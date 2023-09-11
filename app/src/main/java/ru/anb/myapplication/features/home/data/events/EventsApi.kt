package ru.anb.myapplication.features.home.data.events

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import ru.anb.myapplication.features.home.data.ContentApi
import ru.anb.myapplication.features.home.domain.model.EventsModel

interface EventsApi: ContentApi<EventsModel> {

    @GET("/api/events/")
   override suspend fun getAll(): Response<List<EventsModel>>

    @GET("/api/events/latest")
    override suspend fun getLatest(@Query("count") count: Int): Response<List<EventsModel>>

    @GET("/api/events/{event_id}/before")
    override suspend fun getBefore(@Path("event_id")id:Long, @Query("count") count: Int): Response<List<EventsModel>>

    @GET("/api/events/{event_id}/after")
    override suspend fun getAfter(@Path("event_id")id:Long, @Query("count") count: Int): Response<List<EventsModel>>

    @GET("/api/events/{event_id}/newer")
    suspend fun getNewer(@Path("event_id") id: Long): Response<List<EventsModel>>
}
