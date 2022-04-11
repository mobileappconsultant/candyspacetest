package com.example.candyspacestack.domain.usecase

import androidx.paging.PagingData
import com.example.candyspacestack.data.repository.StackSearchRepository
import com.example.candyspacestack.domain.model.SearchUserDomain
import dagger.hilt.android.scopes.ViewModelScoped
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow

@ViewModelScoped
class SearchUserUseCase @Inject constructor(private val searchRepository: StackSearchRepository) {
    fun execute(query: String): Flow<PagingData<SearchUserDomain>> =
        searchRepository.searchUser(query)
}
