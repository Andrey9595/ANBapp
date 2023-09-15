package ru.anb.myapplication.features.home.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import ru.anb.myapplication.core.data.AppDatabase
import ru.anb.myapplication.features.auth.data.PersistentStore
import ru.anb.myapplication.features.home.data.events.EventsApi
import ru.anb.myapplication.features.home.data.events.EventsMediator
import ru.anb.myapplication.features.home.data.posts.PostsApi
import ru.anb.myapplication.features.home.db.events.PostsMediator
import ru.anb.myapplication.features.profile.domain.IsAuthorizedUseCase
import ru.anb.myapplication.features.profile.domain.LogOutUseCase
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class HomeModule {

    @Provides
    @Singleton
    fun provideEventsApi(retrofit: Retrofit): EventsApi {
        return retrofit.create(EventsApi::class.java)
    }
    @Provides
    @Singleton
    fun providePostsApi(retrofit: Retrofit): PostsApi {
        return retrofit.create(PostsApi::class.java)
    }

    @Provides
    @Singleton
    fun provideEventsMediator(api: EventsApi, db: AppDatabase): EventsMediator {
        return EventsMediator(api, db)
    }

    @Provides
    fun provideIsAuthorizedUseCase(persistentStore: PersistentStore): IsAuthorizedUseCase.Base {
        return IsAuthorizedUseCase.Base(persistentStore)
    }

    @Provides
    fun provideLogOutUseCase(persistentStore: PersistentStore): LogOutUseCase.Base {
        return LogOutUseCase.Base(persistentStore)
    }

    @Provides
    @Singleton
    fun providePostsMediator(api: PostsApi, db: AppDatabase): PostsMediator {
        return PostsMediator(api, db)
    }
}