package ru.anb.myapplication.features.auth.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import ru.anb.myapplication.core.domain.AppLoadState
import ru.anb.myapplication.features.auth.domain.SignUpUseCase
import ru.anb.myapplication.features.auth.domain.model.Token
import javax.inject.Inject

@HiltViewModel
class RegistrationViewModel @Inject constructor(private val signUpUseCase: SignUpUseCase) :
    ViewModel() {

    private val _registrationState = MutableStateFlow<AppLoadState<Token>>(AppLoadState.NotLoadedYet())
    val registrationState get() = _registrationState.asStateFlow()

    fun sendRegistrationRequest(login: String, password: String, name: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val result = signUpUseCase.signUp(name, password, login)
            _registrationState.value = result
        }
    }
}