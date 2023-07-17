package ru.anb.myapplication.features.auth.ui


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import ru.anb.myapplication.core.domain.LoadState
import ru.anb.myapplication.features.auth.domain.AuthRepository
import ru.anb.myapplication.features.auth.domain.model.AuthenticationRequest
import ru.anb.myapplication.features.auth.domain.model.Token
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(private val repository: AuthRepository) :
    ViewModel() {

    private val _authState = MutableStateFlow<LoadState<Token>>(LoadState.NotLoadedYet())
    val authState get() = _authState.asStateFlow()

    fun sendAuthRequest(login: String, password: String) {
        viewModelScope.launch {
            val result = repository.auth(AuthenticationRequest(login, password))
            _authState.value = result
        }
    }

}