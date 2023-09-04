package ru.anb.myapplication.features.home.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import ru.anb.myapplication.features.auth.data.PersistentStore
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val persistentStore: PersistentStore
) : ViewModel() {

    private val _isAuthorized = MutableStateFlow(false)
    val isAuthorized get() = _isAuthorized.asStateFlow()

    init {
        isUserAuthorized()
    }
    fun isUserAuthorized() {
        viewModelScope.launch(Dispatchers.IO) {
            _isAuthorized.value = persistentStore.isAuthorized()
        }
    }
}