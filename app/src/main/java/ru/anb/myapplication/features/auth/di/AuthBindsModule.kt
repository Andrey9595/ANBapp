package ru.anb.myapplication.features.auth.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ru.anb.myapplication.features.auth.data.AuthRepositoryImpl
import ru.anb.myapplication.features.auth.data.PersistentStore
import ru.anb.myapplication.features.auth.data.PersistentStoreImpl
import ru.anb.myapplication.features.auth.domain.AuthRepository
import ru.anb.myapplication.features.auth.domain.AuthUseCase

@InstallIn(SingletonComponent::class)
@Module
abstract class AuthBindsModule {

    @Binds
    abstract fun bindAuthRepository(authRepositoryImpl: AuthRepositoryImpl): AuthRepository

    @Binds
    abstract fun bindAuthUseCase(authUseCase: AuthUseCase.Base): AuthUseCase

    @Binds
    abstract fun bindPersistentStore(persistentStore: PersistentStoreImpl): PersistentStore
}