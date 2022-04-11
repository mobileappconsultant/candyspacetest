package com.example.candyspacestack.domain.usecase

import com.example.candyspacestack.data.repository.StackSearchRepository
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Test

@ExperimentalCoroutinesApi
class SearchUserUseCaseTest {
    private lateinit var sut: SearchUserUseCase
    private val githubSearchRepository = mockk<StackSearchRepository>()

    @Test
    fun `given query, when execute is called, then githubSearchRepository searchUser should be called`() =
        runTest {
            sut = SearchUserUseCase(githubSearchRepository)
            val query = "searchQuery"
            coEvery {
                githubSearchRepository.searchUser(query)
            } returns flowOf()

            sut.execute(query)

            coVerify {
                githubSearchRepository.searchUser(query)
            }
        }
}
