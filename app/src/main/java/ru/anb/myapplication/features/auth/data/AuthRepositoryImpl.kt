package ru.anb.myapplication.features.auth.data

import ru.anb.myapplication.R
import ru.anb.myapplication.core.domain.LoadState
import ru.anb.myapplication.features.auth.domain.AuthRepository
import ru.anb.myapplication.features.auth.domain.model.AuthenticationRequest
import ru.anb.myapplication.features.auth.domain.model.Token
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(private val authApi: AuthApi) : AuthRepository {
    override suspend fun auth(request: AuthenticationRequest): LoadState<Token> {
        val result = authApi.sendAuthCode(request)
        return if (result.isSuccessful)
            LoadState.Success(result.body())
        else LoadState.Error(R.string.auth_error_message)
    }
}