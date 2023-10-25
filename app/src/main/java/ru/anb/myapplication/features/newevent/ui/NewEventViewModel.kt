package ru.anb.myapplication.features.newevent.ui

import android.net.Uri
import androidx.core.net.toFile
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import ru.anb.myapplication.core.domain.AppLoadState
import ru.anb.myapplication.features.home.domain.model.AttachmentType
import ru.anb.myapplication.features.home.domain.model.EventCreateRequest
import ru.anb.myapplication.features.home.domain.model.MediaUpload
import ru.anb.myapplication.features.newevent.domain.NewEventsRepository
import javax.inject.Inject

@HiltViewModel
class NewEventViewModel @Inject constructor(private val newEventsRepository: NewEventsRepository) :
    ViewModel() {

    private val _newEventState = MutableStateFlow<AppLoadState<Unit>>(AppLoadState.NotLoadedYet())
    val newEventState get() = _newEventState.asStateFlow()

    fun save(createRequest: EventCreateRequest, uri: Uri, type: AttachmentType?) {
        viewModelScope.launch {
            val result = newEventsRepository.save(createRequest, MediaUpload(uri.toFile()), type)
            _newEventState.value = result
        }
    }
}