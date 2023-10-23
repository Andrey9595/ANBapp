package ru.anb.myapplication.features.newevent.data

import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import ru.anb.myapplication.R
import ru.anb.myapplication.core.domain.LoadState
import ru.anb.myapplication.features.home.domain.model.AttachmentModel
import ru.anb.myapplication.features.home.domain.model.AttachmentType
import ru.anb.myapplication.features.home.domain.model.EventCreateRequest
import ru.anb.myapplication.features.home.domain.model.Media
import ru.anb.myapplication.features.home.domain.model.MediaUpload
import ru.anb.myapplication.features.newevent.domain.NewEventsRepository
import java.io.IOException
import javax.inject.Inject

class NewEventRepositoryImpl @Inject constructor(private val newEventApi: NewEventApi) :
    NewEventsRepository {
    override suspend fun save(
        eventCreateRequest: EventCreateRequest,
        upload: MediaUpload?,
        type: AttachmentType?
    ): LoadState<Unit> {
        val eventWithAttachment = upload?.let {
            upload(it)
        }?.let {
            eventCreateRequest.copy(attachment = type?.let { type ->
                AttachmentModel(
                    it.url,
                    type
                )
            })
        }
        val result = newEventApi.save(eventWithAttachment ?: eventCreateRequest)

        return if (result.isSuccessful) LoadState.Success(Unit)
        else LoadState.Error(R.string.auth_error_message)

    }

    override suspend fun upload(upload: MediaUpload): Media {
        val media = MultipartBody.Part.createFormData(
            "file", upload.file.name, upload.file.asRequestBody()
        )
        val response = newEventApi.upload(media)
//        if (!response.isSuccessful) {
//            throw IOException(response.message())
//        }
        return response.body() ?: throw IOException(response.message())
    }
}