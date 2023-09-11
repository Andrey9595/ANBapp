package ru.anb.myapplication.core.data

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import ru.anb.myapplication.features.home.db.ContentEntity
import ru.anb.myapplication.features.home.db.ContentEntityDao
import ru.anb.myapplication.features.home.db.Convertor
import ru.anb.myapplication.features.home.db.events.EventEntity
import ru.anb.myapplication.features.home.db.events.EventEntityDao
import ru.anb.myapplication.features.home.db.events.EventRemoteKeyDao
import ru.anb.myapplication.features.home.db.events.EventRemoteKeyEntity

@Database(
    entities = [
        EventEntity::class, EventRemoteKeyEntity::class
    ], version = 1, exportSchema = false
)
@TypeConverters(Convertor::class) 
abstract class AppDatabase: RoomDatabase() {

    abstract fun <T: ContentEntity<>>

    abstract fun getEventEntityDao(): EventEntityDao

    abstract fun getEventRemoteKeyDao(): EventRemoteKeyDao
}