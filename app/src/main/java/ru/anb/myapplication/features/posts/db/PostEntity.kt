package ru.anb.myapplication.features.posts.db

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import ru.anb.myapplication.features.home.domain.model.AttachmentModel
import ru.anb.myapplication.features.home.domain.model.CoordinatesModel
import ru.anb.myapplication.features.home.domain.model.PostModel
import ru.anb.myapplication.features.home.domain.model.UserPreview

@Entity
data class PostEntity(
    @PrimaryKey
    val id: Long,
    val authorId: Long,
    val author: String,
    val authorAvatar: String?,
    val authorJob: String?,
    var content: String,
    val published: String?,
    @Embedded
    var coords: CoordinatesModel?,
    var link: String?,
    var likeOwnerIds: List<Long>?,
    var mentionIds: List<Long>,
    val mentionMe: Boolean,
    val likedByMe: Boolean,
    @Embedded
    val attachment: AttachmentModel?,
    var playBtnPressed: Boolean = false,
    val ownedByMe: Boolean,
    val users: Map<Long, UserPreview>
) {

    fun toPostModel(): PostModel {
        return PostModel(
            id,
            authorId,
            author,
            authorAvatar,
            authorJob,
            content,
            published,
            coords,
            link,
            likeOwnerIds,
            mentionIds,
            mentionMe,
            likedByMe,
            attachment,
            playBtnPressed,
            ownedByMe,
            users
        )
    }
}

