package ru.anb.myapplication.features.home.db.posts

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import ru.anb.myapplication.features.home.db.ContentEntity
import ru.anb.myapplication.features.home.domain.model.AttachmentModel
import ru.anb.myapplication.features.home.domain.model.CoordinatesModel
import ru.anb.myapplication.features.home.domain.model.PostModel
import ru.anb.myapplication.features.home.domain.model.UserPreview
import java.time.LocalDateTime

@Entity
data class PostEntity(
    @PrimaryKey
    override val id: Long,
    val authorId: Long,
    val author: String,
    val authorAvatar: String?,
    val authorJob: String?,
    var content: String,
    val published: LocalDateTime?,
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
): ContentEntity<PostModel>() {

    override fun toDomainModel(): PostModel {
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

