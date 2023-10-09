package ru.anb.myapplication.features.home.domain.model

data class EventCreateRequest (
    val id: Long  =0,
    val content: String,
    val datetime: String,
//    val coords: CoordinatesModel?,
    val type: String = "ONLINE",
//    val attachment: Attachment?,
    val link: String = "www",
    val speakerIds: List<Long> = emptyList()
)