package ru.anb.myapplication.features.profile.data

import ru.anb.myapplication.R
import ru.anb.myapplication.core.domain.AppLoadState
import ru.anb.myapplication.features.profile.domain.UserRepository
import ru.anb.myapplication.features.profile.domain.model.User
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(private val userApi: UserApi) : UserRepository {
    override suspend fun getUserById(id: Int): AppLoadState<User> {
        return userApi.getUserById(id).body()?.let { AppLoadState.Success(it) }
            ?: AppLoadState.Error(R.string.auth_error_message)
    }
}