package ru.anb.myapplication.features.home.domain

import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import ru.anb.myapplication.features.home.domain.model.EventsModel

interface EventsRepository {

     fun getPagedEvents(): Flow<PagingData<EventsModel>>
}