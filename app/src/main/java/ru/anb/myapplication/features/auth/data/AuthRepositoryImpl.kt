package ru.anb.myapplication.features.auth.data

import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody.Companion.toRequestBody
import ru.anb.myapplication.R
import ru.anb.myapplication.core.domain.LoadState
import ru.anb.myapplication.features.auth.domain.AuthRepository
import ru.anb.myapplication.features.auth.domain.model.AuthenticationBody
import ru.anb.myapplication.features.auth.domain.model.Token
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(private val authApi: AuthApi) : AuthRepository {
    override suspend fun auth(request: AuthenticationBody): LoadState<Token> {
        val result = authApi.sendAuthCode(request)
        return if (result.isSuccessful)
            LoadState.Success(result.body())
        else LoadState.Error(R.string.auth_error_message)
    }

    override suspend fun registration(name: String, pass: String, login: String): LoadState<Token> {
        val result = authApi.registerUser(name, pass, login)
        return if (result.isSuccessful)
            LoadState.Success(result.body())
        else LoadState.Error(R.string.auth_error_message)
    }
}