package ru.anb.myapplication.features.home.domain.model

import ru.anb.myapplication.features.events.db.EventEntity

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
    val likedByMe: Boolean,
    val speakerIds: List<Long>,
    val participantsIds: List<Long>,
    val participatedByMe: Boolean,
    val attachment: AttachmentModel?,//
    val link: String?,
    val ownedByMe: Boolean,
    val users: Map<Long, UserPreview>
) {
    fun toEventEntity(): EventEntity {
        return EventEntity(
            id,
            authorId,
            author,
            authorAvatar,
            authorJob,
            content,
            datetime,
            published,
            type,
            likeOwnerIds,
            likedByMe,
            speakerIds,
            participantsIds,
            participatedByMe,
            attachment,
            link,
            ownedByMe,
            users
        )
    }
}
