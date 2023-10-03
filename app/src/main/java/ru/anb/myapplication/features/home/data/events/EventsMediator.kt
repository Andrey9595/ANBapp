package ru.anb.myapplication.features.home.data.events

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import ru.anb.myapplication.core.data.AppDatabase
import ru.anb.myapplication.features.home.db.events.EventEntity
import ru.anb.myapplication.features.home.db.events.EventRemoteKeyEntity
import ru.anb.myapplication.features.home.db.events.KeyType
import java.io.IOException

@OptIn(ExperimentalPagingApi::class)
class EventsMediator(private val api: EventsPagingApi, private val db: AppDatabase) :
    RemoteMediator<Int, EventEntity>() {

    private val keyDao = db.getEventRemoteKeyDao()
    private val eventEntityDao = db.getEventEntityDao()

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, EventEntity>
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
                                EventRemoteKeyEntity(
                                    KeyType.BEFORE,
                                    body.last().id
                                ),
                                EventRemoteKeyEntity(
                                    KeyType.AFTER,
                                    body.first().id
                                )
                            )
                        )
                        eventEntityDao.clear()
                    }

                    LoadType.APPEND -> {
                        if (body.isNotEmpty()) {
                            keyDao.insert(
                                EventRemoteKeyEntity(
                                    KeyType.BEFORE,
                                    body.last().id
                                )
                            )
                        }
                    }

                    else -> Unit
                }

                eventEntityDao.insert(
                    body.map { it.toEventEntity() }
                )
            }

            return MediatorResult.Success(body.isEmpty())
        } catch (e: IOException) {
            return MediatorResult.Error(e)
        }
    }

    companion object {
        const val PAGE_SIZE = 10
        const val FIRST_PAGE = 1
    }
}

