package com.example.candyspacestack.domain.usecase

import androidx.paging.PagingData
import com.example.candyspacestack.data.repository.StackSearchRepository
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@ViewModelScoped
class SearchUserUseCase @Inject constructor(private val searchRepository: StackSearchRepository) {
    suspend fun execute(query: String): Flow<PagingData<Any>> = TODO()
}
