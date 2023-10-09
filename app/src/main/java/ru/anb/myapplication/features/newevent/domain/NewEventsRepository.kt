package ru.anb.myapplication.features.newevent.domain

import ru.anb.myapplication.core.domain.LoadState
import ru.anb.myapplication.features.home.domain.model.EventCreateRequest

interface NewEventsRepository {

    suspend fun save(eventCreateRequest: EventCreateRequest): LoadState<Unit>
}