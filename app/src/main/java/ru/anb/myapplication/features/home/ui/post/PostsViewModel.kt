package ru.anb.myapplication.features.home.ui.post

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import ru.anb.myapplication.features.home.domain.model.PostModel
import ru.anb.myapplication.features.home.domain.posts.PostsRepository
import javax.inject.Inject

@HiltViewModel
class PostsViewModel @Inject constructor(private val repository: PostsRepository) : ViewModel() {

    fun getPagedPosts(): Flow<PagingData<PostModel>> {
        return repository.getPagedPosts().cachedIn(viewModelScope)
    }

    fun like(postModel: PostModel) {
        viewModelScope.launch {
            repository.likeById(postModel)
        }
    }

    fun dislike(postModel: PostModel) {
        viewModelScope.launch {
            repository.dislikeById(postModel)
        }
    }

    fun removePost(postModel: PostModel) {
        viewModelScope.launch {
            repository.remove(postModel.id)
        }
    }
}