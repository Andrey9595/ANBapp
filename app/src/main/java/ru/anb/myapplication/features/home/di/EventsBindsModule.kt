package ru.anb.myapplication.features.home.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ru.anb.myapplication.features.home.data.EventsRepositoryImpl
import ru.anb.myapplication.features.home.domain.EventsRepository

@InstallIn(SingletonComponent::class)
@Module
abstract class EventsBindsModule {

    @Binds
    abstract fun bindEventsRepository(eventsRepositoryImpl: EventsRepositoryImpl): EventsRepository
}