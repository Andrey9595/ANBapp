package ru.anb.myapplication.features.newpost.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import ru.anb.myapplication.features.newpost.data.NewPostApi
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class NewPostModule {

    @Provides
    @Singleton
    fun provideNewPostApi(retrofit: Retrofit): NewPostApi {
        return retrofit.create(NewPostApi::class.java)
    }
}