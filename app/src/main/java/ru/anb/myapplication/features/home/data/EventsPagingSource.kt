package ru.anb.myapplication.features.home.data

import androidx.paging.PagingSource
import androidx.paging.PagingState
import ru.anb.myapplication.features.home.domain.model.EventsModel

class EventsPagingSource(private val eventsApi: EventsApi) : PagingSource<Int, EventsModel>() {
    override fun getRefreshKey(state: PagingState<Int, EventsModel>): Int? {
        return 1
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, EventsModel> {
        val page = params.key ?: 1
        return kotlin.runCatching {
            eventsApi.getAll().body()!!
        }.fold(
            onSuccess = {
                LoadResult.Page(
                    data = it,
                    prevKey = null,
                    nextKey = if (it.isEmpty()) null else page + 1
                )
            }, onFailure = { LoadResult.Error(it) }
        )
    }
}