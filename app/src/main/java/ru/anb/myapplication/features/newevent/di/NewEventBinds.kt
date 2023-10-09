package ru.anb.myapplication.features.newevent.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ru.anb.myapplication.features.newevent.data.NewEventRepositoryImpl
import ru.anb.myapplication.features.newevent.domain.NewEventsRepository

@InstallIn(SingletonComponent::class)
@Module
abstract class NewEventBinds {

    @Binds
    abstract fun bindNewEventRepository(newEventRepositoryImpl: NewEventRepositoryImpl): NewEventsRepository
}