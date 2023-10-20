package ru.anb.myapplication.features.posts.domain

import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import ru.anb.myapplication.features.home.domain.BaseContentCRUD
import ru.anb.myapplication.features.home.domain.model.PostModel

interface PostsRepository : BaseContentCRUD<PostModel> {

    fun getPagedPosts(): Flow<PagingData<PostModel>>

   suspend fun remove(id: Long)
}