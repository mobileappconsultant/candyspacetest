package com.example.candyspacestack.data.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.candyspacestack.data.api.StackExchangeApi

class SearchPagingSource(
    private val searchApi: StackExchangeApi,
    private val query: String,
) : PagingSource<Int, String>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, String> {
        val currentPage = params.key ?: 1
        return LoadResult.Error(Exception())
    }

    override fun getRefreshKey(state: PagingState<Int, String>): Int? {
        return state.anchorPosition
    }
}
