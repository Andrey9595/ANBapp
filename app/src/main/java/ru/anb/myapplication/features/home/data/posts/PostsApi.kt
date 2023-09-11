package ru.anb.myapplication.features.home.data.posts

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import ru.anb.myapplication.features.home.data.ContentApi
import ru.anb.myapplication.features.home.domain.model.PostModel

interface PostsApi: ContentApi<PostModel> {

    @GET("/api/posts/")
    override suspend fun getAll(): Response<List<PostModel>>

    @GET("/api/posts/latest/")
    override suspend fun getLatest(@Query("count") count: Int): Response<List<PostModel>>

    @GET("/api/posts/{post_id}/before/")
    override suspend fun getBefore(@Path("post_id") id: Long, @Query("count") count: Int): Response<List<PostModel>>

    @GET("/api/posts/{post_id}/after/")
    override suspend fun getAfter(@Path("post_id") id: Long, @Query("count") count: Int): Response<List<PostModel>>
}