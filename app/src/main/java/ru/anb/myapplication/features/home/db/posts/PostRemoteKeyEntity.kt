package ru.anb.myapplication.features.home.db.posts

import androidx.room.Entity
import androidx.room.PrimaryKey
import ru.anb.myapplication.features.home.db.events.KeyType

@Entity
data class PostRemoteKeyEntity(
    @PrimaryKey
    val type: KeyType,
    val key: Long
)
