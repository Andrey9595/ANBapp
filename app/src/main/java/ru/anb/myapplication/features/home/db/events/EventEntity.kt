package ru.anb.myapplication.features.home.db.events

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import ru.anb.myapplication.features.home.domain.model.Attachment
import ru.anb.myapplication.features.home.domain.model.EventsModel
import ru.anb.myapplication.features.home.domain.model.UserPreview

@Entity
data class EventEntity(
    @PrimaryKey
    val id: Long,
    val authorId: Long, //
    val author: String,
    val authorAvatar: String?,
    val authorJob: String?, //
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
    @Embedded
    val attachment: Attachment?,//
    val link: String?,
    val ownedByMe: Boolean,
    val users: Map<Long, UserPreview>
) {

    fun toEventModel(): EventsModel {
        return EventsModel(
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
