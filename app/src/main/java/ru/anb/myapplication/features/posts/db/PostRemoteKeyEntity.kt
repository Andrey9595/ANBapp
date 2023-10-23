package ru.anb.myapplication.features.posts.db

import androidx.room.Entity
import androidx.room.PrimaryKey
import ru.anb.myapplication.features.events.db.KeyType

@Entity
data class PostRemoteKeyEntity(
    @PrimaryKey
    val type: KeyType,
    val key: Long
)
