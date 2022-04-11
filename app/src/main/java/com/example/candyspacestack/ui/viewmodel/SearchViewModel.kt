package com.example.candyspacestack.ui.viewmodel

import androidx.lifecycle.ViewModel
import com.example.candyspacestack.domain.mapper.SearchMapper
import com.example.candyspacestack.domain.usecase.SearchUserUseCase
import com.example.candyspacestack.utils.DispatcherProvider
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val searchMapper: SearchMapper,
    private val searchUserUseCase: SearchUserUseCase,
    private val dispatchers: DispatcherProvider
) : ViewModel()
