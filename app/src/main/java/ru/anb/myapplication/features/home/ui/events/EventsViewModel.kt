package ru.anb.myapplication.features.home.ui.events

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import ru.anb.myapplication.features.home.domain.events.EventsRepository
import ru.anb.myapplication.features.home.domain.model.EventsModel
import javax.inject.Inject


@HiltViewModel
class EventsViewModel @Inject constructor(private val repository: EventsRepository) : ViewModel() {


    fun sendGetAll(): Flow<PagingData<EventsModel>> {
        return repository.getPagedEvents().cachedIn(viewModelScope)
    }
}