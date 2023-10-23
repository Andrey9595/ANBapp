package ru.anb.myapplication.features.newevent.data

import okhttp3.MultipartBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part
import ru.anb.myapplication.features.home.domain.model.EventCreateRequest
import ru.anb.myapplication.features.home.domain.model.EventsModel
import ru.anb.myapplication.features.home.domain.model.Media

interface NewEventApi {

    @POST("api/events")
    suspend fun save(@Body eventCreateRequest: EventCreateRequest): Response<EventsModel>

    @Multipart
    @POST("api/media")
    suspend fun upload(@Part media: MultipartBody.Part): Response<Media>
}