package ru.anb.myapplication.features.home.domain.model

data class EventsModel(
    val id: Long,
    val authorId: Long,
    val author: String,
    val authorAvatar: String?,
    val authorJob: String?,
    val content: String,
    val datetime: String,
    val published: String,
//    val coords: Coordinates?,
    val type: String,
    val likeOwnerIds: List<Long>,
    val likedByMe:Boolean,
    val speakerIds: List<Long>,
    val participantsIds: List<Long>,
    val participatedByMe: Boolean,
//    val attachment: Attachment?,
    val link: String?,
    val ownedByMe: Boolean,
//    val users: Map<Long, UserPreview>
)
