package ru.anb.myapplication.features.home.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ru.anb.myapplication.features.events.data.EventsRepositoryImpl
import ru.anb.myapplication.features.events.domain.EventsRepository
import ru.anb.myapplication.features.home.db.job.JobRepositoryImpl
import ru.anb.myapplication.features.home.domain.job.JobRepository
import ru.anb.myapplication.features.posts.data.data.PostsRepositoryImpl
import ru.anb.myapplication.features.posts.domain.PostsRepository
import ru.anb.myapplication.features.profile.domain.IsAuthorizedUseCase
import ru.anb.myapplication.features.profile.domain.LogOutUseCase

@InstallIn(SingletonComponent::class)
@Module
abstract class HomeBindsModule {

    @Binds
    abstract fun bindEventsRepository(eventsRepositoryImpl: EventsRepositoryImpl): EventsRepository

    @Binds
    abstract fun bindPostsRepository(postsRepositoryImpl: PostsRepositoryImpl): PostsRepository

    @Binds
    abstract fun bindIsAuthAuthorized(isAuthorizedUseCase: IsAuthorizedUseCase.Base): IsAuthorizedUseCase

    @Binds
    abstract fun bindLogOutUseCase(logOutUseCase: LogOutUseCase.Base): LogOutUseCase

    @Binds
    abstract fun bindJobRepository(jobRepositoryImpl: JobRepositoryImpl): JobRepository
}