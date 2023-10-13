package ru.anb.myapplication.features.home.db.events

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import ru.anb.myapplication.core.data.AppDatabase
import ru.anb.myapplication.features.home.data.posts.PostsApi
import ru.anb.myapplication.features.home.db.posts.PostEntity
import ru.anb.myapplication.features.home.db.posts.PostRemoteKeyEntity
import java.io.IOException
import javax.inject.Inject

@OptIn(ExperimentalPagingApi::class)
class PostsMediator @Inject constructor(private val api: PostsApi, private val db: AppDatabase) :
    RemoteMediator<Int, PostEntity>() {

    private val keyDao = db.getPostRemoteKeyDao()
    private val postEntityDao = db.getPostEntityDao()

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, PostEntity>
    ): MediatorResult {
        try {
            val response = when (loadType) {
                LoadType.REFRESH -> {
                    api.getLatest(state.config.initialLoadSize)
                }

                LoadType.PREPEND -> {
                    return MediatorResult.Success(
                        true
                    )
                }

                LoadType.APPEND -> {
                    val id = keyDao.min() ?: return MediatorResult.Success(
                        false
                    )
                    api.getBefore(id, state.config.pageSize)
                }
            }

//            if (!response.isSuccessful) {
//                throw ApiError(response.code(), response.message())
//            }
            val body = response.body() ?: throw IllegalAccessException()
            //println("RESPONSE_BODY: ${body}")

            db.withTransaction {
                when (loadType) {
                    LoadType.REFRESH -> {
                        keyDao.clear()
                        keyDao.insert(
                            listOf(
                                PostRemoteKeyEntity(
                                    KeyType.BEFORE,
                                    body.last().id
                                ),
                                PostRemoteKeyEntity(
                                    KeyType.AFTER,
                                    body.first().id
                                )
                            )
                        )
                        postEntityDao.clear()
                    }

                    LoadType.APPEND -> {
                        if (body.isNotEmpty()) {
                            keyDao.insert(
                                PostRemoteKeyEntity(
                                    KeyType.BEFORE,
                                    body.last().id
                                )
                            )
                        }
                    }

                    else -> Unit
                }

                postEntityDao.insert(
                    body.map { it.toPostEntity() }
                )
            }

            return MediatorResult.Success(body.isEmpty())
        } catch (e: IOException) {
            return MediatorResult.Error(e)
        }
    }

    companion object{
        const val PAGE_SIZE = 10
        const val FIRST_PAGE = 1
    }
}