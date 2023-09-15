package ru.anb.myapplication.features.home.ui.post

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import ru.anb.myapplication.features.home.domain.model.PostModel
import ru.anb.myapplication.features.home.domain.posts.PostsRepository
import javax.inject.Inject

@HiltViewModel
class PostsViewModel @Inject constructor(private val repository: PostsRepository) : ViewModel() {

    fun getPagedPosts(): Flow<PagingData<PostModel>> {
        return repository.getPagedPosts().cachedIn(viewModelScope)
    }
}