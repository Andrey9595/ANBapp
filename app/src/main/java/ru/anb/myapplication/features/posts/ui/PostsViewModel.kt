package ru.anb.myapplication.features.posts.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import ru.anb.myapplication.core.domain.AppLoadState
import ru.anb.myapplication.features.home.domain.model.PostModel
import ru.anb.myapplication.features.posts.domain.PostsRepository
import javax.inject.Inject

@HiltViewModel
class PostsViewModel @Inject constructor(private val repository: PostsRepository) : ViewModel() {

    fun getPagedPosts(): Flow<PagingData<PostModel>> {
        return repository.getPagedPosts().cachedIn(viewModelScope)
    }

    private val _errorFlow = MutableSharedFlow<AppLoadState<Unit>>()
    val errorFlow get() = _errorFlow.asSharedFlow()

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
            repository.remove(postModel.id).let { _errorFlow.emit(it) }
        }
    }
}