package ru.anb.myapplication.features.newpost.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ru.anb.myapplication.features.newpost.data.NewPostRepositoryImpl
import ru.anb.myapplication.features.newpost.domain.NewPostRepository

@InstallIn(SingletonComponent::class)
@Module
abstract class NewPostBinds {

    @Binds
    abstract fun bindNewPostRepository(newPostRepositoryImpl: NewPostRepositoryImpl): NewPostRepository
}