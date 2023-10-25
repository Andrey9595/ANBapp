package ru.anb.myapplication.features.newevent.domain

import ru.anb.myapplication.core.domain.AppLoadState
import ru.anb.myapplication.features.home.domain.model.AttachmentType
import ru.anb.myapplication.features.home.domain.model.EventCreateRequest
import ru.anb.myapplication.features.home.domain.model.Media
import ru.anb.myapplication.features.home.domain.model.MediaUpload

interface NewEventsRepository {

    suspend fun save(eventCreateRequest: EventCreateRequest, upload: MediaUpload? = null, type: AttachmentType? = null): AppLoadState<Unit>

    suspend fun upload(upload: MediaUpload): Media
}