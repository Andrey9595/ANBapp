package ru.anb.myapplication.features.newevent.data

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST
import ru.anb.myapplication.features.home.domain.model.EventCreateRequest
import ru.anb.myapplication.features.home.domain.model.EventsModel

interface NewEventApi {

    @POST("api/events")
    suspend fun save(@Body eventCreateRequest: EventCreateRequest): Response<EventsModel>
}