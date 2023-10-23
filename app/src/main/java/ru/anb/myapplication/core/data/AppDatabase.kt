package ru.anb.myapplication.core.data

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import ru.anb.myapplication.features.events.db.EventEntity
import ru.anb.myapplication.features.events.db.EventEntityDao
import ru.anb.myapplication.features.events.db.EventRemoteKeyDao
import ru.anb.myapplication.features.events.db.EventRemoteKeyEntity
import ru.anb.myapplication.features.home.db.Convertor
import ru.anb.myapplication.features.home.db.job.JobEntity
import ru.anb.myapplication.features.home.db.job.JobsDao
import ru.anb.myapplication.features.posts.db.PostEntity
import ru.anb.myapplication.features.posts.db.PostEntityDao
import ru.anb.myapplication.features.posts.db.PostRemoteKeyDao
import ru.anb.myapplication.features.posts.db.PostRemoteKeyEntity

@Database(
    entities = [
        EventEntity::class, EventRemoteKeyEntity::class, PostEntity::class, PostRemoteKeyEntity::class, JobEntity::class
    ], version = 1, exportSchema = false
)
@TypeConverters(Convertor::class)
abstract class AppDatabase : RoomDatabase() {

    abstract fun getEventEntityDao(): EventEntityDao

    abstract fun getEventRemoteKeyDao(): EventRemoteKeyDao

    abstract fun getPostEntityDao(): PostEntityDao

    abstract fun getPostRemoteKeyDao(): PostRemoteKeyDao

    abstract fun getJobDao(): JobsDao
}