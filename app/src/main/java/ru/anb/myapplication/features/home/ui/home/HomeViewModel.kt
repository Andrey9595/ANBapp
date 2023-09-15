package ru.anb.myapplication.features.home.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import ru.anb.myapplication.features.profile.domain.IsAuthorizedUseCase
import ru.anb.myapplication.features.profile.domain.LogOutUseCase
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val isAuthorizedUseCase: IsAuthorizedUseCase,
    private val logOutUseCase: LogOutUseCase
) : ViewModel() {

    private val _isAuthorized = MutableStateFlow(false)

    val isAuthorized get() = _isAuthorized.asStateFlow()

    fun isUserAuthorized() {
        viewModelScope.launch(Dispatchers.IO) {
            _isAuthorized.value = isAuthorizedUseCase.isAuthorized()
        }
    }
    fun logOut() {
        viewModelScope.launch(Dispatchers.IO) {
            val logOutResult = logOutUseCase.logOut()
            if (logOutResult)
                _isAuthorized.value = isAuthorizedUseCase.isAuthorized()
        }
    }
}