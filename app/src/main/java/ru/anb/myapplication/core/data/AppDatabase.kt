package ru.anb.myapplication.core.data

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import ru.anb.myapplication.features.home.db.EventEntity
import ru.anb.myapplication.features.home.db.EventEntityDao
import ru.anb.myapplication.features.home.db.EventRemoteKeyDao
import ru.anb.myapplication.features.home.db.EventRemoteKeyEntity
import ru.anb.myapplication.features.home.db.Convertor

@Database(
    entities = [
        EventEntity::class, EventRemoteKeyEntity::class
    ], version = 1, exportSchema = false
)
@TypeConverters(Convertor::class) 
abstract class AppDatabase: RoomDatabase() {

    abstract fun getEventEntityDao(): EventEntityDao

    abstract fun getEventRemoteKeyDao(): EventRemoteKeyDao
}