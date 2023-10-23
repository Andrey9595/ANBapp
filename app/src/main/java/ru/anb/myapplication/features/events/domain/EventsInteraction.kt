package ru.anb.myapplication.features.events.domain

import ru.anb.myapplication.features.home.domain.ContentInteraction
import ru.anb.myapplication.features.home.domain.model.EventsModel

interface EventsInteraction : ContentInteraction<EventsModel> {
    fun onTakePartBtn(event: EventsModel)
}