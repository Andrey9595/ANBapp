package ru.anb.myapplication.features.home.db

import androidx.paging.PagingSource
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import ru.anb.myapplication.features.home.db.events.EventEntity

interface ContentEntityDao<T: Any> {

    fun getAll(): PagingSource<Int, T>


    suspend fun insert(event: T)


    suspend fun insert(events: List<T>)


    fun  getPagingSource(): PagingSource<Int, T>


    suspend fun clear()
}
