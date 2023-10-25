package ru.anb.myapplication.features.auth.domain


import ru.anb.myapplication.core.domain.AppLoadState
import ru.anb.myapplication.features.auth.domain.model.AuthenticationBody
import ru.anb.myapplication.features.auth.domain.model.Token

interface AuthRepository {

    suspend fun auth(request: AuthenticationBody): AppLoadState<Token>

    suspend fun registration(name: String, pass: String, login: String): AppLoadState<Token>
}