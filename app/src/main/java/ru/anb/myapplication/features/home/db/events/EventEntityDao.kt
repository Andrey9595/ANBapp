package ru.anb.myapplication.features.home.db.events

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import ru.anb.myapplication.features.home.db.ContentEntityDao

@Dao
interface EventEntityDao: ContentEntityDao<EventEntity> {

    @Query("SELECT * FROM EventEntity ORDER BY id DESC")
    override fun getAll(): PagingSource<Int, EventEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    override suspend fun insert(event: EventEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    override suspend fun insert(events: List<EventEntity>)

    @Query("SELECT * FROM EventEntity ORDER  BY id DESC")
    override fun  getPagingSource(): PagingSource<Int, EventEntity>

    @Query("DELETE FROM EventEntity")
    override suspend fun clear()
}