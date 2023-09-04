package ru.anb.myapplication.features.profile.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ru.anb.myapplication.features.profile.data.UserRepositoryImpl
import ru.anb.myapplication.features.profile.domain.UserRepository

@InstallIn(SingletonComponent::class)
@Module
abstract class UserBindsModel {

    @Binds
    abstract fun bindUserRepository(userRepositoryImpl: UserRepositoryImpl): UserRepository
}