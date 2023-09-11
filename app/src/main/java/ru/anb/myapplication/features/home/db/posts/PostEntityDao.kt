package ru.anb.myapplication.features.home.db.posts

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import ru.anb.myapplication.features.home.db.ContentEntityDao

@Dao
interface PostEntityDao: ContentEntityDao<PostEntity> {
    @Query("SELECT * FROM PostEntity ORDER BY id DESC")
    override fun getAll(): PagingSource<Int, PostEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    override suspend fun insert(post: PostEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    override suspend fun insert(posts: List<PostEntity>)

    @Query("SELECT * FROM PostEntity ORDER  BY id DESC")
    override fun  getPagingSource(): PagingSource<Int, PostEntity>

    @Query("DELETE FROM PostEntity")
    override suspend fun clear()
}