package ru.anb.myapplication.features.auth.domain

import ru.anb.myapplication.core.domain.AppLoadState
import ru.anb.myapplication.features.auth.data.PersistentStore
import ru.anb.myapplication.features.auth.domain.model.AuthenticationBody
import ru.anb.myapplication.features.auth.domain.model.Token
import javax.inject.Inject

interface SignInUseCase {

    suspend fun signIn(login: String, password: String): AppLoadState<Token>

    class Base @Inject constructor(
        private val authRepository: AuthRepository,
        private val persistentStore: PersistentStore
    ) : SignInUseCase {

        override suspend fun signIn(login: String, password: String): AppLoadState<Token> {
            val result = authRepository.auth(AuthenticationBody(login, password))
            if (result is AppLoadState.Success) {
                persistentStore.saveToken(result.data.token)
                persistentStore.savaId(result.data.id)
            }
            return result
        }
    }
}