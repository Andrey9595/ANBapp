package ru.anb.myapplication.features.profile.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import ru.anb.myapplication.R
import ru.anb.myapplication.core.domain.AppLoadState
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

    private val _userState = MutableStateFlow<AppLoadState<User>>(AppLoadState.NotLoadedYet())
    val userState get() = _userState.asStateFlow()

    init {
        loadUser()
    }

    fun loadUser() {
        viewModelScope.launch(Dispatchers.IO) {
            _userState.value = AppLoadState.Loading()
            val id = persistentStore.getUserId()
            if (id == null) {
                _userState.value = AppLoadState.Error(R.string.something_went_wrong)
                return@launch
            }
            val result = userRepository.getUserById(id = id)
            _userState.value = result
        }
    }
}