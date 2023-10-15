package ru.anb.myapplication.features.newpost.domain

import ru.anb.myapplication.core.domain.LoadState
import ru.anb.myapplication.features.newpost.domain.model.PostRequest

interface NewPostRepository {

    suspend fun create(post: PostRequest): LoadState<Unit>
}