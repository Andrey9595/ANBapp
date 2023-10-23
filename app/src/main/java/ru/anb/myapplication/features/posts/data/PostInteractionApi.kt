package ru.anb.myapplication.features.posts.data

import retrofit2.Response
import retrofit2.http.DELETE
import retrofit2.http.POST
import retrofit2.http.Path
import ru.anb.myapplication.features.newpost.domain.model.PostResponse

interface PostInteractionApi {

    @DELETE("api/posts/{id}")
    suspend fun removeById(@Path("id") id: Long): Response<Unit>

    @POST("api/posts/{id}/likes")
    suspend fun likeById(@Path("id") id: Long): Response<PostResponse>

    @DELETE("api/posts/{id}/likes")
    suspend fun dislikeById(@Path("id") id: Long): Response<PostResponse>
}