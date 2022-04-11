package com.example.candyspacestack.ui.composable

import android.os.Build
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.paging.PagingData
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.candyspacestack.CoroutineTestRule
import com.example.candyspacestack.ui.MainActivity
import com.example.candyspacestack.ui.model.User
import com.example.candyspacestack.utils.Constants.IDLE_TAG
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.HiltTestApplication
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@ExperimentalCoroutinesApi
@HiltAndroidTest
@RunWith(RobolectricTestRunner::class)
@Config(
    manifest = Config.DEFAULT_MANIFEST_NAME,
    sdk = [Build.VERSION_CODES.O_MR1],
    application = HiltTestApplication::class
)
class SearchListTest {
    @get:Rule(order = 0)
    var hiltTestRule = HiltAndroidRule(this)

    @get:Rule(order = 1)
    var composeTestRule = createAndroidComposeRule<MainActivity>()

    @ExperimentalCoroutinesApi
    @get:Rule
    val coroutineTestRule = CoroutineTestRule()

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
