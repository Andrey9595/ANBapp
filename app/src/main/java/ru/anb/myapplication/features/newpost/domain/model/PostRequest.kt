package ru.anb.myapplication.features.newpost.domain.model

data class PostRequest(
    val id: Long =0,
    val content: String,
//    val coords: Coordinates?,
    val link: String? = "www",
//    val attachment: Attachment?,
//    val mentionIds: List<Long>?
)
