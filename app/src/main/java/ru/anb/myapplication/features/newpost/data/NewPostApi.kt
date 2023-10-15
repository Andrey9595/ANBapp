package ru.anb.myapplication.features.newpost.data

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST
import ru.anb.myapplication.features.newpost.domain.model.PostRequest
import ru.anb.myapplication.features.newpost.domain.model.PostResponse

interface NewPostApi {

    @POST("/api/posts/")
    suspend fun save(@Body postRequest: PostRequest): Response<PostResponse>
}