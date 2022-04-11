package com.example.candyspacestack.data.repository

import androidx.paging.PagingData
import com.example.candyspacestack.domain.model.SearchUserDomain
import kotlinx.coroutines.flow.Flow

interface StackSearchRepository {
    fun searchUser(query: String): Flow<PagingData<SearchUserDomain>>
}
