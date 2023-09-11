package ru.anb.myapplication.features.home.data

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import ru.anb.myapplication.features.home.domain.model.EventsModel

interface ContentApi<T> {

    suspend fun getAll(): Response<List<T>>


    suspend fun getLatest(count: Int): Response<List<T>>


    suspend fun getBefore(id:Long, count: Int): Response<List<T>>


    suspend fun getAfter(id:Long, count: Int): Response<List<T>>

}
