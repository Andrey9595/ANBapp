package ru.anb.myapplication.features.auth.data

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST
import ru.anb.myapplication.features.auth.domain.model.AuthenticationRequest
import ru.anb.myapplication.features.auth.domain.model.Token

interface AuthApi {
    @POST("/api/users/authentication/")
    suspend fun sendAuthCode(@Body body: AuthenticationRequest): Response<Token>
}