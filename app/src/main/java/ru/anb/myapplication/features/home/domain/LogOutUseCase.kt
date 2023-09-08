package ru.anb.myapplication.features.home.domain

import ru.anb.myapplication.features.auth.data.PersistentStore
import javax.inject.Inject

interface LogOutUseCase {

    suspend fun logOut(): Boolean

    class Base @Inject constructor(private val persistentStore: PersistentStore) : LogOutUseCase {
        override suspend fun logOut(): Boolean {
            return persistentStore.removeToken()
        }
    }
}