package com.example.candyspacestack.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import com.example.candyspacestack.domain.mapper.SearchMapper
import com.example.candyspacestack.data.api.StackExchangeApi
import com.example.candyspacestack.data.paging.SearchPagingSource
import com.example.candyspacestack.domain.model.SearchUserDomain
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class StackSearchRepositoryImpl @Inject constructor(
    private val searchMapper: SearchMapper,
    private val searchApi: StackExchangeApi,
    private val pagingConfig: PagingConfig
) : StackSearchRepository {
    override fun searchUser(query: String): Flow<PagingData<SearchUserDomain>> = Pager(pagingConfig) {
        SearchPagingSource(searchApi, query)
    }.flow.map {
        it.map { schema ->
            searchMapper.mapToDomain(schema)
        }
    }
}
