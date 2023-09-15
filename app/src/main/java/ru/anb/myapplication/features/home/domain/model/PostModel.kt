package ru.anb.myapplication.features.home.domain.model

import ru.anb.myapplication.features.home.db.posts.PostEntity

data class PostModel(
    val id: Long,
    val authorId: Long,
    val author: String,
    val authorAvatar: String?,
    val authorJob: String?,
    var content: String,
    val published: String?,
    var coords: CoordinatesModel?,
    var link: String?,
    var likeOwnerIds: List<Long>?,
    var mentionIds: List<Long>,
    val mentionMe: Boolean,
    val likedByMe: Boolean,
    val attachment: AttachmentModel?,
    var playBtnPressed: Boolean = false,
    val ownedByMe: Boolean,
    val users: Map<Long, UserPreview>
) {
    fun toPostEntity(): PostEntity {
        return PostEntity(
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
