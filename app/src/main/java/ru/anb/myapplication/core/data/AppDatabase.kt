package ru.anb.myapplication.core.data

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import ru.anb.myapplication.features.home.db.Convertor
import ru.anb.myapplication.features.home.db.events.EventEntity
import ru.anb.myapplication.features.home.db.events.EventEntityDao
import ru.anb.myapplication.features.home.db.events.EventRemoteKeyDao
import ru.anb.myapplication.features.home.db.events.EventRemoteKeyEntity
import ru.anb.myapplication.features.home.db.posts.PostEntity
import ru.anb.myapplication.features.home.db.posts.PostEntityDao
import ru.anb.myapplication.features.home.db.posts.PostRemoteKeyDao
import ru.anb.myapplication.features.home.db.posts.PostRemoteKeyEntity

@Database(
    entities = [
        EventEntity::class, EventRemoteKeyEntity::class, PostEntity::class, PostRemoteKeyEntity::class
    ], version = 1, exportSchema = false
)
@TypeConverters(Convertor::class) 
abstract class AppDatabase: RoomDatabase() {

    abstract fun getEventEntityDao(): EventEntityDao

    abstract fun getEventRemoteKeyDao(): EventRemoteKeyDao

    abstract fun getPostEntityDao(): PostEntityDao

    abstract fun getPostRemoteKeyDao(): PostRemoteKeyDao
}