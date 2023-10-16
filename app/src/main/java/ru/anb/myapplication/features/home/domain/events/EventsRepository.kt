package ru.anb.myapplication.features.home.domain.events

import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import ru.anb.myapplication.features.home.domain.BaseContentCRUD
import ru.anb.myapplication.features.home.domain.model.EventsModel

interface EventsRepository : BaseContentCRUD<EventsModel> {

    fun getPagedEvents(): Flow<PagingData<EventsModel>>

    suspend fun takeParticipation(id: Long)

    suspend fun removeParticipation(id: Long)

}