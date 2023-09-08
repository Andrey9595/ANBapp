package ru.anb.myapplication.features.home.db.events

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class EventRemoteKeyEntity (
    @PrimaryKey
    val type: KeyType,
    val key: Long
) {

}
enum class KeyType {
    AFTER,
    BEFORE
}