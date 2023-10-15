package ru.anb.myapplication.features.newpost.data

import ru.anb.myapplication.R
import ru.anb.myapplication.core.domain.LoadState
import ru.anb.myapplication.features.newpost.domain.NewPostRepository
import ru.anb.myapplication.features.newpost.domain.model.PostRequest
import javax.inject.Inject

class NewPostRepositoryImpl @Inject constructor(private val newPostApi: NewPostApi): NewPostRepository {
    override suspend fun create(post: PostRequest): LoadState<Unit> {
       val result = newPostApi.save(post).body()
        return if (result != null)
            LoadState.Success(Unit)
        else LoadState.Error(R.string.auth_error_message)
    }

}