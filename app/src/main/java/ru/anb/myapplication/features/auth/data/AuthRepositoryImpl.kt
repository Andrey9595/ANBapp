package ru.anb.myapplication.features.auth.data

import ru.anb.myapplication.R
import ru.anb.myapplication.core.domain.AppLoadState
import ru.anb.myapplication.features.auth.domain.AuthRepository
import ru.anb.myapplication.features.auth.domain.model.AuthenticationBody
import ru.anb.myapplication.features.auth.domain.model.Token
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(private val authApi: AuthApi) : AuthRepository {
    override suspend fun auth(request: AuthenticationBody): AppLoadState<Token> {
        val result = authApi.sendAuthCode(request)
        return result.body()?.let {
            AppLoadState.Success(it)
        } ?: AppLoadState.Error(R.string.auth_error_message)
    }

    override suspend fun registration(name: String, pass: String, login: String): AppLoadState<Token> {
        val result = authApi.registerUser(name, pass, login)
        return result.body()?.let {
            AppLoadState.Success(it)
        } ?: AppLoadState.Error(R.string.auth_error_message)
    }
}