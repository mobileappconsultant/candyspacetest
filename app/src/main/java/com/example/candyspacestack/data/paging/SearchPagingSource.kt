package com.example.candyspacestack.data.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.candyspacestack.data.api.StackExchangeApi
import com.example.candyspacestack.data.model.ItemSchema
import com.example.candyspacestack.utils.Constants

class SearchPagingSource(
    private val searchApi: StackExchangeApi,
    private val query: String,
) : PagingSource<Int, ItemSchema>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ItemSchema> {
        val currentPage = params.key ?: 1
        return try {
            val items = searchApi.searchUser(
                query = query,
                sort = "name",
                order = "ASC",
                page = currentPage,
                pageSize = Constants.NO_PER_PAGE,
                site = "stackoverflow"
            )
            val endOfPaginationReached = items.items.isEmpty()
            if (endOfPaginationReached.not()) {
                LoadResult.Page(
                    data = items.items,
                    prevKey = if (currentPage == 1) null else currentPage - 1,
                    nextKey = if (endOfPaginationReached) null else currentPage + 1
                )
            } else {
                LoadResult.Page(
                    data = emptyList(),
                    prevKey = null,
                    nextKey = null
                )
            }
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, ItemSchema>): Int? {
        return state.anchorPosition
    }
}
