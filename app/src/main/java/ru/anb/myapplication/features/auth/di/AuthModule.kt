package ru.anb.myapplication.features.auth.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import ru.anb.myapplication.features.auth.data.AuthApi
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class AuthModule {

    @Provides
    @Singleton
    fun provideAuthApi(retrofit: Retrofit): AuthApi {
        return retrofit.create(AuthApi::class.java)
    }
}