package ru.anb.myapplication.features.events.data

import retrofit2.Response
import retrofit2.http.DELETE
import retrofit2.http.POST
import retrofit2.http.Path
import ru.anb.myapplication.features.home.domain.model.EventsModel

interface EventsInteractionApi {

    @POST("api/events/{event_id}/likes")
    suspend fun likeById(@Path("event_id") id: Long): Response<EventsModel>

    @DELETE("api/events/{event_id}/likes")
    suspend fun dislikeById(@Path("event_id") id: Long): Response<EventsModel>

    @POST("api/events/{event_id}/participants")
    suspend fun createParticipant(@Path("event_id") id: Long) :Response<EventsModel>

    @DELETE("api/events/{event_id}/participants")
    suspend fun removeParticipant(@Path("event_id") id: Long) :Response<EventsModel>

    @DELETE("api/events/{event_id}")
    suspend fun removeById(@Path("event_id") id: Long): Response<Unit>

}