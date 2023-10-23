package ru.anb.myapplication.features.newevent.domain

import ru.anb.myapplication.core.domain.LoadState
import ru.anb.myapplication.features.home.domain.model.AttachmentType
import ru.anb.myapplication.features.home.domain.model.EventCreateRequest
import ru.anb.myapplication.features.home.domain.model.Media
import ru.anb.myapplication.features.home.domain.model.MediaUpload

interface NewEventsRepository {

    suspend fun save(eventCreateRequest: EventCreateRequest, upload: MediaUpload? = null, type: AttachmentType? = null): LoadState<Unit>

    suspend fun upload(upload: MediaUpload): Media
}