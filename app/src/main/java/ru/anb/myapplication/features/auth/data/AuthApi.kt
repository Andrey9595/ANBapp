package ru.anb.myapplication.features.auth.data

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST
import ru.anb.myapplication.features.auth.domain.model.AuthenticationBody
import ru.anb.myapplication.features.auth.domain.model.Token

interface AuthApi {
    @POST("/api/users/authentication/")
    suspend fun sendAuthCode(@Body body: AuthenticationBody): Response<Token>

    @FormUrlEncoded
    @POST("/api/users/registration/")
    suspend fun registerUser(
        @Field("login") login: String,
        @Field("password") pass: String,
        @Field("name") name: String
    ): Response<Token>
}