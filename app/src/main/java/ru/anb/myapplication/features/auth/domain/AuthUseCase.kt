package ru.anb.myapplication.features.auth.domain

import ru.anb.myapplication.core.domain.LoadState
import ru.anb.myapplication.features.auth.data.PersistentStore
import ru.anb.myapplication.features.auth.domain.model.Token
import javax.inject.Inject

interface AuthUseCase {

    suspend fun signUp(login: String, password: String, name: String): LoadState<Token>

    class Base @Inject constructor(
        private val authRepository: AuthRepository,
        private val persistentStore: PersistentStore
    ) : AuthUseCase {

        override suspend fun signUp(
            login: String,
            password: String,
            name: String
        ): LoadState<Token> {
            val result = authRepository.registration(name, password, login)
            if (result is LoadState.Success)
                persistentStore.saveToken(result.data.token)
            return result
        }
    }
}