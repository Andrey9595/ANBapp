package ru.anb.myapplication.features.profile.domain

import ru.anb.myapplication.core.domain.AppLoadState
import ru.anb.myapplication.features.profile.domain.model.User

interface UserRepository {

    suspend fun getUserById(id: Int): AppLoadState<User>
}