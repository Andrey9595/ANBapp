package ru.anb.myapplication.features.home.domain.posts

import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import ru.anb.myapplication.features.home.domain.model.PostModel

interface PostsRepository {

    fun getPagedPosts(): Flow<PagingData<PostModel>>
}