package ru.anb.myapplication.features.auth.domain

import ru.anb.myapplication.core.domain.AppLoadState
import ru.anb.myapplication.features.auth.data.PersistentStore
import ru.anb.myapplication.features.auth.domain.model.Token
import javax.inject.Inject

interface SignUpUseCase {

    suspend fun signUp(login: String, password: String, name: String): AppLoadState<Token>

    class Base @Inject constructor(
        private val authRepository: AuthRepository,
        private val persistentStore: PersistentStore
    ) : SignUpUseCase {

        override suspend fun signUp(
            login: String,
            password: String,
            name: String
        ): AppLoadState<Token> {
            val result = authRepository.registration(name, password, login)
            if (result is AppLoadState.Success) {
                persistentStore.saveToken(result.data.token)
                persistentStore.savaId(result.data.id)
            }
            return result
        }
    }
}