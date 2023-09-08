package ru.anb.myapplication.features.profile.domain

import ru.anb.myapplication.features.auth.data.PersistentStore
import javax.inject.Inject

interface IsAuthorizedUseCase {

    suspend fun isAuthorized(): Boolean

    class Base @Inject constructor ( private val persistentStore: PersistentStore):
        IsAuthorizedUseCase {
        override suspend fun isAuthorized(): Boolean {
          return persistentStore.isAuthorized()
        }
    }
}
