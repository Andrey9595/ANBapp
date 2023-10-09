package ru.anb.myapplication.features.newevent.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import ru.anb.myapplication.core.domain.LoadState
import ru.anb.myapplication.features.home.domain.model.EventCreateRequest
import ru.anb.myapplication.features.newevent.domain.NewEventsRepository
import javax.inject.Inject

@HiltViewModel
class NewEventViewModel @Inject constructor(private val newEventsRepository: NewEventsRepository) :
    ViewModel() {

    private val _newEventState = MutableStateFlow<LoadState<Unit>>(LoadState.NotLoadedYet())
    val newEventState get() = _newEventState.asStateFlow()

    fun save(createRequest: EventCreateRequest) {
        viewModelScope.launch {
            val result = newEventsRepository.save(createRequest)
            _newEventState.value = result
        }
    }
}