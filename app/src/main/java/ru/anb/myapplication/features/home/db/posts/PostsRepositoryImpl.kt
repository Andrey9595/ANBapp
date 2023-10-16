package ru.anb.myapplication.features.home.db.posts

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import ru.anb.myapplication.core.data.AppDatabase
import ru.anb.myapplication.features.home.data.posts.PostInteractionApi
import ru.anb.myapplication.features.home.db.events.PostsMediator
import ru.anb.myapplication.features.home.domain.model.PostModel
import ru.anb.myapplication.features.home.domain.posts.PostsRepository
import javax.inject.Inject

class PostsRepositoryImpl @Inject constructor(
    private val postsMediator: PostsMediator,
    private val db: AppDatabase,
    private val postInteractionApi: PostInteractionApi
) : PostsRepository {
    private val postEntityDao = db.getPostEntityDao()

    @OptIn(ExperimentalPagingApi::class)
    override fun getPagedPosts(): Flow<PagingData<PostModel>> {
        return Pager(
            config = PagingConfig(10),
            pagingSourceFactory = { postEntityDao.getPagingSource() },
            remoteMediator = postsMediator
        ).flow.map { it.map { posts -> posts.toPostModel() } }
    }

    override suspend fun remove(id: Long) {

        val result = postInteractionApi.removeById(id)
        if (result.isSuccessful)
            postEntityDao.removeById(id)
    }

    override suspend fun likeById(t: PostModel) {
        val result = postInteractionApi.likeById(t.id)
        if (result.isSuccessful)
            postEntityDao.likeById(t.id)
    }

    override suspend fun dislikeById(t: PostModel) {
        val result = postInteractionApi.dislikeById(t.id)
        if (result.isSuccessful)
            postEntityDao.likeById(t.id)
    }
}