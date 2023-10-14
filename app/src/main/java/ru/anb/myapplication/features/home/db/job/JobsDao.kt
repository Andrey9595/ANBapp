package ru.anb.myapplication.features.home.db.job

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface JobsDao {

    @Query("SELECT * FROM JobEntity ORDER BY id ASC")
    fun getAll(): Flow<List<JobEntity>>

    @Query("SELECT * FROM JobEntity WHERE userId = :userId ORDER BY id DESC")
    suspend fun getByAuthorId(userId: Long): List<JobEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(jobs: JobEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(jobs: List<JobEntity>)

    @Query("DELETE FROM JobEntity WHERE id = :id")
    suspend fun removeById(id: Long)

    @Query("DELETE FROM JobEntity")
    suspend fun clear()
}