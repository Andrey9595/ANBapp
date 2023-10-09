package ru.anb.myapplication.features.newevent.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import ru.anb.myapplication.features.newevent.data.NewEventApi
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class NewEventModule {

    @Provides
    @Singleton
    fun provideNewEventApi(retrofit: Retrofit): NewEventApi {
        return retrofit.create(NewEventApi::class.java)
    }
}