package ru.anb.myapplication.features.posts.data

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import ru.anb.myapplication.features.home.domain.model.PostModel

interface PostsApi {

    @GET("/api/posts/")
    suspend fun getAll(): Response<List<PostModel>>

    @GET("/api/posts/latest/")
    suspend fun getLatest(@Query("count") count: Int): Response<List<PostModel>>

    @GET("/api/posts/{post_id}/before/")
    suspend fun getBefore(
        @Path("post_id") id: Long,
        @Query("count") count: Int
    ): Response<List<PostModel>>

    @GET("/api/posts/{post_id}/after/")
    suspend fun getAfter(
        @Path("post_id") id: Long,
        @Query("count") count: Int
    ): Response<List<PostModel>>
}