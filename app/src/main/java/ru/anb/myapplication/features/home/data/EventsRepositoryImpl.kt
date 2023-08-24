package ru.anb.myapplication.features.home.data

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import ru.anb.myapplication.core.data.AppDatabase
import ru.anb.myapplication.features.home.domain.EventsRepository
import ru.anb.myapplication.features.home.domain.model.EventsModel
import javax.inject.Inject

class EventsRepositoryImpl @Inject constructor(
    private val eventsMediator: EventsMediator,
    private val db: AppDatabase
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
}