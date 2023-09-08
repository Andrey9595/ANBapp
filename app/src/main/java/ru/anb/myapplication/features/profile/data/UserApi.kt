package ru.anb.myapplication.features.profile.data

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import ru.anb.myapplication.features.profile.domain.model.User

interface UserApi {

    @GET("/api/users/{user_id}/")
    suspend fun getUserById(@Path("user_id") id: Int): Response<User>
}