package ru.anb.myapplication.features.newevent.data

import ru.anb.myapplication.R
import ru.anb.myapplication.core.domain.LoadState
import ru.anb.myapplication.features.home.domain.model.EventCreateRequest
import ru.anb.myapplication.features.newevent.domain.NewEventsRepository
import javax.inject.Inject

class NewEventRepositoryImpl @Inject constructor(private val newEventApi: NewEventApi) :
    NewEventsRepository {
    override suspend fun save(eventCreateRequest: EventCreateRequest): LoadState<Unit> {
        val result = newEventApi.save(eventCreateRequest)

        if (result.isSuccessful) return LoadState.Success(Unit)
        else return LoadState.Error(R.string.auth_error_message)
    }
}