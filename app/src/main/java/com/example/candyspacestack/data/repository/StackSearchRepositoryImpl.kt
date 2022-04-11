package com.example.candyspacestack.data.repository

import androidx.paging.PagingConfig
import com.example.candyspacestack.domain.mapper.SearchMapper
import com.example.candyspacestack.data.api.StackExchangeApi
import javax.inject.Inject

class StackSearchRepositoryImpl @Inject constructor(
    private val searchMapper: SearchMapper,
    private val searchApi: StackExchangeApi,
    private val pagingConfig: PagingConfig
) : StackSearchRepository
