package ru.anb.myapplication.features.profile.data

import ru.anb.myapplication.R
import ru.anb.myapplication.core.domain.LoadState
import ru.anb.myapplication.features.profile.domain.UserRepository
import ru.anb.myapplication.features.profile.domain.model.User
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(private val userApi: UserApi) : UserRepository {
    override suspend fun getUserById(id: Int): LoadState<User> {
        val result = userApi.getUserById(id)
        return if (result.isSuccessful && result.body() != null)
            LoadState.Success(result.body()!!)
        else LoadState.Error(R.string.something_went_wrong)
    }
}