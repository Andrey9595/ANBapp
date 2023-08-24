package ru.anb.myapplication.features.home.data

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import ru.anb.myapplication.features.home.domain.model.EventsModel

interface EventsApi {

    @GET("/api/events/")
    suspend fun getAll(): Response<List<EventsModel>>

    @GET("events/latest")
    suspend fun getLatest(@Query("count") count: Int): Response<List<EventsModel>>

    @GET("events/{event_id}/before")
    suspend fun getBefore(@Path("event_id")id:Long, @Query("count") count: Int): Response<List<EventsModel>>

    @GET("events/{event_id}/after")
    suspend fun getAfter(@Path("event_id")id:Long, @Query("count") count: Int): Response<List<EventsModel>>

    @GET("events/{event_id}/newer")
    suspend fun getNewer(@Path("event_id") id: Long): Response<List<EventsModel>>
}
