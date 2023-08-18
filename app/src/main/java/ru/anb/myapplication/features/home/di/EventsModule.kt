package ru.anb.myapplication.features.home.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import ru.anb.myapplication.features.home.data.EventsApi
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class EventsModule {

    @Provides
    @Singleton
    fun provideEventsApi(retrofit: Retrofit): EventsApi {
        return retrofit.create(EventsApi::class.java)
    }
}