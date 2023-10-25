package ru.anb.myapplication.features.newpost.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import ru.anb.myapplication.core.domain.AppLoadState
import ru.anb.myapplication.features.newpost.domain.NewPostRepository
import ru.anb.myapplication.features.newpost.domain.model.PostRequest
import javax.inject.Inject

@HiltViewModel
class NewPostViewModel @Inject constructor(private val newPostRepository: NewPostRepository) :
    ViewModel() {

    private val _newPostState = MutableStateFlow<AppLoadState<Unit>>(AppLoadState.NotLoadedYet())

    val newPostState get() = _newPostState.asStateFlow()

    fun createNewPost(postRequest: PostRequest) {
        viewModelScope.launch {
            val result = newPostRepository.create(postRequest)
            _newPostState.value = result
        }
    }
}