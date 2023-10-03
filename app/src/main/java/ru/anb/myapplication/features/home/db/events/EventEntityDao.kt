package ru.anb.myapplication.features.home.db.events

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface EventEntityDao {

    @Query("SELECT * FROM EventEntity ORDER BY id DESC")
    fun getAll(): PagingSource<Int, EventEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(event: EventEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(events: List<EventEntity>)

    @Query("SELECT * FROM EventEntity ORDER  BY id DESC")
    fun getPagingSource(): PagingSource<Int, EventEntity>

    @Query("DELETE FROM EventEntity")
    suspend fun clear()

    @Query(
        """
        UPDATE EventEntity SET
        likedByMe = CASE WHEN likedByMe THEN 0 ELSE 1 END
        WHERE  id = :id
        """
    )
    suspend fun likeById(id: Long)

    @Query(
        """
        UPDATE EventEntity SET
        participatedByMe = CASE WHEN participatedByMe  THEN 0 ELSE 1 END
        WHERE id = :eventId
    """
    )
    suspend fun participate(eventId: Long)

    @Query("DELETE FROM EventEntity WHERE id = :id")
    suspend fun removeById(id: Long)
}