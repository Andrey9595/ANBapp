package ru.anb.myapplication.features.home.domain.model

data class EventCreateRequest (
    val id: Long,
    val content: String,
    val datetime: String,
    val coords: CoordinatesModel?,
    val type: String,
    val attachment: Attachment?,
    val link: String?,
    val speakerIds: List<Long>
)