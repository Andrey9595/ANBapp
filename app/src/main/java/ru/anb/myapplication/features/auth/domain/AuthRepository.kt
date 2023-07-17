package ru.anb.myapplication.features.auth.domain


import ru.anb.myapplication.core.domain.LoadState
import ru.anb.myapplication.features.auth.domain.model.AuthenticationBody
import ru.anb.myapplication.features.auth.domain.model.Token

interface AuthRepository {

    suspend fun auth(request: AuthenticationBody): LoadState<Token>

    suspend fun registration(name: String, pass: String, login: String): LoadState<Token>
}