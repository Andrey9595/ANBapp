package ru.anb.myapplication.features.profile.ui

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import ru.anb.myapplication.R
import ru.anb.myapplication.core.domain.LoadState
import ru.anb.myapplication.features.auth.data.PersistentStore
import ru.anb.myapplication.features.profile.domain.UserRepository
import ru.anb.myapplication.features.profile.domain.model.User
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val userRepository: UserRepository,
    private val persistentStore: PersistentStore
) :
    ViewModel() {

    private val _userState = MutableStateFlow<LoadState<User>>(LoadState.NotLoadedYet())
    val userState get() = _userState.asStateFlow()

    init {
        loadUser()
    }

    fun loadUser() {
        viewModelScope.launch(Dispatchers.IO) {
            Log.d("aaa", "started")
            _userState.value = LoadState.Loading()
            val id = persistentStore.getUserId()
            Log.d("aaa", "id = $id")
            if (id == null) {
                _userState.value = LoadState.Error(R.string.something_went_wrong)
                return@launch
            }
            val result = userRepository.getUserById(id = id)
            Log.d("aaa", "result = $result")
            _userState.value = result
        }
    }
}