package ru.anb.myapplication.features.home.data

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import ru.anb.myapplication.features.home.domain.EventsRepository
import ru.anb.myapplication.features.home.domain.model.EventsModel
import javax.inject.Inject

class EventsRepositoryImpl @Inject constructor(private val eventsApi: EventsApi) :
    EventsRepository {

    override fun getPagedEvents(): Flow<PagingData<EventsModel>> {
    return Pager(config = PagingConfig(10), pagingSourceFactory = {EventsPagingSource(eventsApi)}).flow
    }
}