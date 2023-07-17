package ru.anb.myapplication.features.auth.domain


import ru.anb.myapplication.core.domain.LoadState
import ru.anb.myapplication.features.auth.domain.model.AuthenticationRequest
import ru.anb.myapplication.features.auth.domain.model.Token

interface AuthRepository {

    suspend fun auth(request: AuthenticationRequest): LoadState<Token>
}