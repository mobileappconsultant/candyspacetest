package com.example.candyspacestack.data.paging

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.paging.PagingSource
import com.example.candyspacestack.CoroutineTestRule
import com.example.candyspacestack.data.api.StackExchangeApi
import com.example.candyspacestack.data.model.ItemSchema
import com.example.candyspacestack.data.model.SearchResponseSchema
import com.example.candyspacestack.domain.model.SearchUserDomain
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class SearchPagingSourceTest {
    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    var coroutineTestRule = CoroutineTestRule()

    private val mockedItemSchema = mockk<ItemSchema>()

    private val mockedResponseSchema = SearchResponseSchema(
        hasMore = false,
        items = listOf(mockedItemSchema, mockedItemSchema, mockedItemSchema),
        quotaMax = 0,
        quotaRemaining = 0

    )

    private val mockSearchApi = mockk<StackExchangeApi>()
    lateinit var sut: SearchPagingSource

    @Before
    fun setup() {
        sut = SearchPagingSource(mockSearchApi, "")
    }

    @Test
    fun `given when paging source load is called and an error occurs, it should return error`() =
        runTest {
            val error = RuntimeException("404", Throwable())
            coEvery {
                mockSearchApi.searchUser(
                    any(),
                    any(),
                    any(),
                    any(),
                    any(),
                    any()
                )
            } throws error
            val expectedResult = PagingSource.LoadResult.Error<Int, SearchUserDomain>(error)
            assertEquals(
                expectedResult,
                sut.load(
                    PagingSource.LoadParams.Refresh(
                        key = 0,
                        loadSize = 1,
                        placeholdersEnabled = false
                    )
                )
            )
        }

    @Test
    fun `given when paging source load is called and request is successful, it should return correct data`() =
        runTest {
            coEvery {
                mockSearchApi.searchUser(
                    any(),
                    any(),
                    any(),
                    any(),
                    any(),
                    any()
                )
            } returns mockedResponseSchema
            val expectedResult = PagingSource.LoadResult.Page(
                data = listOf(mockedItemSchema, mockedItemSchema, mockedItemSchema),
                prevKey = 1,
                nextKey = 3
            )
            assertEquals(
                expectedResult,
                sut.load(
                    PagingSource.LoadParams.Append(
                        key = 2,
                        loadSize = 1,
                        placeholdersEnabled = false
                    )
                )
            )
        }

    @Test
    fun `given when paging source load is called and the list is empty, prev key and next key should be null`() =
        runTest {
            coEvery {
                mockSearchApi.searchUser(
                    any(),
                    any(),
                    any(),
                    any(),
                    any(),
                    any()
                )
            } returns mockedResponseSchema.copy(items = listOf())
            val expectedResult = PagingSource.LoadResult.Page(
                data = listOf(),
                prevKey = null,
                nextKey = null
            )
            assertEquals(
                expectedResult,
                sut.load(
                    PagingSource.LoadParams.Append(
                        key = 2,
                        loadSize = 1,
                        placeholdersEnabled = false
                    )
                )
            )
        }
}
