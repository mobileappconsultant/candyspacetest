package com.example.candyspacestack.ui.viewmodel

import com.example.candyspacestack.CoroutineTestRule
import com.example.candyspacestack.dispatchers.TestDispatcherProvider
import com.example.candyspacestack.domain.mapper.SearchMapper
import com.example.candyspacestack.domain.usecase.SearchUserUseCase
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.advanceTimeBy
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class SearchViewModelTest {
    @ExperimentalCoroutinesApi
    @get:Rule
    val coroutineTestRule = CoroutineTestRule()

    private lateinit var sut: SearchViewModel
    private val searchMapper = SearchMapper()
    private val searchUserUseCase = mockk<SearchUserUseCase>()
    private val coroutineProvider = TestDispatcherProvider()

    @Before
    fun setUp() {
        sut = SearchViewModel(searchMapper, searchUserUseCase, coroutineProvider)
    }

    @Test
    fun `given query when searchUser is executed, verify that searchUserUseCase is executed`() =
        runTest {
            coEvery {
                searchUserUseCase.execute(any())
            } returns flowOf()

            sut.searchUser("query")

            advanceTimeBy(300L)

            coVerify {
                searchUserUseCase.execute("query")
            }
        }
}
