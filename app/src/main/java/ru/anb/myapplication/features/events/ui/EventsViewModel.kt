package ru.anb.myapplication.features.events.ui

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
import ru.anb.myapplication.features.events.domain.EventsRepository
import ru.anb.myapplication.features.home.domain.model.EventsModel
import javax.inject.Inject


@HiltViewModel
class EventsViewModel @Inject constructor(private val repository: EventsRepository) : ViewModel() {


    fun sendGetAll(): Flow<PagingData<EventsModel>> {
        return repository.getPagedEvents().cachedIn(viewModelScope)
    }

    private val _errorFlow = MutableSharedFlow<AppLoadState<Unit>>()
    val errorFlow get() = _errorFlow.asSharedFlow()

    fun like(eventsModel: EventsModel) {
        viewModelScope.launch {
            repository.likeById(eventsModel)
        }
    }

    fun disLike(eventsModel: EventsModel) {
        viewModelScope.launch {
            repository.dislikeById(eventsModel)
        }
    }

    fun takePart(eventsModel: EventsModel) {
        viewModelScope.launch {
            repository.takeParticipation(eventsModel.id)
        }
    }

    fun removeParticipation(eventsModel: EventsModel) {
        viewModelScope.launch {
            repository.removeParticipation(eventsModel.id)
        }
    }

    fun removeEvent(eventsModel: EventsModel) {
        viewModelScope.launch {
            repository.remove(eventsModel.id).let { _errorFlow.emit(it) }
        }
    }
}