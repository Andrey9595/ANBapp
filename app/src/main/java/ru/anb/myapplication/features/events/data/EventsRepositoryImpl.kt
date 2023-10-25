package ru.anb.myapplication.features.events.data

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import ru.anb.myapplication.core.data.AppDatabase
import ru.anb.myapplication.core.domain.AppLoadState
import ru.anb.myapplication.features.events.domain.EventsRepository
import ru.anb.myapplication.features.home.domain.model.EventsModel
import javax.inject.Inject

class EventsRepositoryImpl @Inject constructor(
    private val eventsMediator: EventsMediator,
    private val db: AppDatabase,
    private val eventsInteractionApi: EventsInteractionApi
) : EventsRepository {
    private val eventEntityDao = db.getEventEntityDao()

    @OptIn(ExperimentalPagingApi::class)
    override fun getPagedEvents(): Flow<PagingData<EventsModel>> {
        return Pager(
            config = PagingConfig(10),
            pagingSourceFactory = { eventEntityDao.getPagingSource() },
            remoteMediator = eventsMediator
        ).flow.map { it.map { entity -> entity.toEventModel() } }
    }

    override suspend fun takeParticipation(id: Long) {
        eventEntityDao.participate(id)
        eventsInteractionApi.createParticipant(id)
    }

    override suspend fun removeParticipation(id: Long) {
        eventEntityDao.participate(id)
        eventsInteractionApi.removeParticipant(id)
    }

    override suspend fun likeById(t: EventsModel) {
        eventEntityDao.likeById(t.id)
        eventsInteractionApi.likeById(t.id)
    }

    override suspend fun dislikeById(t: EventsModel) {
        eventEntityDao.likeById(t.id)
        eventsInteractionApi.dislikeById(t.id)
    }

    override suspend fun remove(id: Long): AppLoadState<Unit> {
        val result = eventsInteractionApi.removeById(id)
        return if (result.isSuccessful) {
            eventEntityDao.removeById(id)
            AppLoadState.Success(Unit)
        } else AppLoadState.Error(msgString = result.errorBody()?.string())
    }
}