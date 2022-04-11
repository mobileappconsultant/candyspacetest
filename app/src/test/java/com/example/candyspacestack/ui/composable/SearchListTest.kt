package com.example.candyspacestack.ui.composable

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.paging.PagingData
import androidx.paging.compose.collectAsLazyPagingItems
import coil.annotation.ExperimentalCoilApi
import com.example.candyspacestack.ui.model.User
import com.example.candyspacestack.utils.Constants.IDLE_TAG
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.flow.flowOf
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

@ExperimentalCoilApi
@RunWith(RobolectricTestRunner::class)
class SearchListTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    private val mockedName = "Sam"
    private val mockedName2 = "Akisanya"

    private val mockedSearchItem = mockk<User>().apply {
        every { id } returns 1
        every { name } returns mockedName
        every { reputation } returns 18
        every { imageUrl } returns ""
    }

    private val mockedSearchItem2 = mockk<User>().apply {
        every { id } returns 12
        every { name } returns mockedName2
        every { reputation } returns 18
        every { imageUrl } returns ""
    }

    @Test
    fun `given SearchList Composable when PagingItems is available, then the correct data should be displayed`() {
        val searchItems = flowOf(PagingData.from(listOf(mockedSearchItem, mockedSearchItem2)))

        composeTestRule.setContent {
            searchItems.collectAsLazyPagingItems()

            SearchList(searchResult = searchItems.collectAsLazyPagingItems()) {
            }
        }
        composeTestRule.onNodeWithText(mockedName).assertIsDisplayed()
        composeTestRule.onNodeWithText(mockedName2).assertIsDisplayed()
        composeTestRule.onNodeWithTag(IDLE_TAG).assertDoesNotExist()
    }

    @Test
    fun `given SearchList Composable when PagingItems is not available, then the correct data should be displayed`() {
        val searchItems = flowOf(PagingData.from(listOf<User>()))

        composeTestRule.setContent {
            searchItems.collectAsLazyPagingItems()

            SearchList(searchResult = searchItems.collectAsLazyPagingItems()) {
            }
        }
        composeTestRule.onNodeWithTag(IDLE_TAG).assertIsDisplayed()
    }
}
