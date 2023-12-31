package ru.anb.myapplication.features.home.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import ru.anb.myapplication.core.data.AppDatabase
import ru.anb.myapplication.features.auth.data.PersistentStore
import ru.anb.myapplication.features.events.data.EventsInteractionApi
import ru.anb.myapplication.features.events.data.EventsMediator
import ru.anb.myapplication.features.events.data.EventsPagingApi
import ru.anb.myapplication.features.events.db.PostsMediator
import ru.anb.myapplication.features.home.db.job.JobsApi
import ru.anb.myapplication.features.home.db.job.JobsDao
import ru.anb.myapplication.features.posts.data.PostInteractionApi
import ru.anb.myapplication.features.posts.data.PostsApi
import ru.anb.myapplication.features.profile.domain.IsAuthorizedUseCase
import ru.anb.myapplication.features.profile.domain.LogOutUseCase
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class HomeModule {

    @Provides
    @Singleton
    fun provideEventsApi(retrofit: Retrofit): EventsPagingApi {
        return retrofit.create(EventsPagingApi::class.java)
    }

    @Provides
    @Singleton
    fun providePostsApi(retrofit: Retrofit): PostsApi {
        return retrofit.create(PostsApi::class.java)
    }

    @Provides
    @Singleton
    fun provideEventsMediator(api: EventsPagingApi, db: AppDatabase): EventsMediator {
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
    fun providePostsMediator(api: PostsApi, db: AppDatabase): PostsMediator { // TODO: remove]
        return PostsMediator(api, db)
    }

    @Provides
    @Singleton
    fun provideEventsInteractionApi(retrofit: Retrofit): EventsInteractionApi {
        return retrofit.create(EventsInteractionApi::class.java)
    }

    @Provides
    @Singleton
    fun providePostInteractionApi(retrofit: Retrofit): PostInteractionApi {
        return retrofit.create(PostInteractionApi::class.java)
    }

    @Provides
    @Singleton
    fun provideJobsApi(retrofit: Retrofit): JobsApi {
        return retrofit.create(JobsApi::class.java)
    }

    @Provides
    @Singleton
    fun provideJobDao(appDatabase: AppDatabase): JobsDao {
        return appDatabase.getJobDao()
    }
}
