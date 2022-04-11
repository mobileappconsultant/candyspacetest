package com.example.candyspacestack.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.map
import com.example.candyspacestack.domain.mapper.SearchMapper
import com.example.candyspacestack.domain.usecase.SearchUserUseCase
import com.example.candyspacestack.ui.model.User
import com.example.candyspacestack.utils.DispatcherProvider
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val searchMapper: SearchMapper,
    private val searchUserUseCase: SearchUserUseCase,
    private val dispatchers: DispatcherProvider
) : ViewModel() {
    private val _searchResult = MutableStateFlow<PagingData<User>>(PagingData.empty())
    val searchResult = _searchResult.asStateFlow()

    fun searchUser(query: String) {
        _searchResult.value = PagingData.empty()
        viewModelScope.launch(dispatchers.main) {
            // Delay is necessary to reset the pagedlist before sending another request
            delay(LIST_RESET_DELAY)
            searchUserUseCase.execute(query)
                .cachedIn(viewModelScope)
                .collect {
                    _searchResult.value = it.map { domain ->
                        searchMapper.mapToPresentation(domain)
                    }
                }
        }
    }

    companion object {
        private const val LIST_RESET_DELAY = 100L
    }
}
