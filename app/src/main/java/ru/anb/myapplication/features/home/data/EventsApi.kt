package ru.anb.myapplication.features.home.data

import retrofit2.Response
import retrofit2.http.GET
import ru.anb.myapplication.features.home.domain.model.EventsModel

interface EventsApi {

    @GET("/api/events/")
    suspend fun getAll(): Response<List<EventsModel>>
}