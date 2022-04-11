package com.example.candyspacestack.ui.composable

import android.os.Build
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performImeAction
import androidx.compose.ui.test.performTextInput
import com.example.candyspacestack.CoroutineTestRule
import com.example.candyspacestack.ui.MainActivity
import com.example.candyspacestack.ui.theme.CandySpaceStackTheme
import com.example.candyspacestack.utils.Constants.SEARCH_BUTTON_TEXT_TAG
import com.example.candyspacestack.utils.Constants.TEXT_FIELD_TEXT_TAG
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.HiltTestApplication
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Assert.assertEquals
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
class SearchComponentTest {
    @get:Rule(order = 0)
    var hiltTestRule = HiltAndroidRule(this)

    @get:Rule(order = 1)
    var composeTestRule = createAndroidComposeRule<MainActivity>()

    @ExperimentalCoroutinesApi
    @get:Rule
    val coroutineTestRule = CoroutineTestRule()

    @Test
    fun `given when SearchComponent composable is displayed, data is entered and the search is clicked, assert that the query is returned`() {
        var actualText = ""
        val expectedQuery = "Hello there"
        composeTestRule.setContent {
            CandySpaceStackTheme {
                SearchComponent {
                    actualText = it
                }
            }
        }

        val textField = composeTestRule.onNodeWithTag(TEXT_FIELD_TEXT_TAG)
        val searchButton = composeTestRule.onNodeWithTag(SEARCH_BUTTON_TEXT_TAG)
        textField.performClick()
        textField.performTextInput(expectedQuery)

        searchButton.performClick()

        assertEquals(expectedQuery, actualText)
    }

    @Test
    fun `given when SearchComponent composable is displayed, data is entered and the user performs search from the keyboard, assert that the query is returned`() {
        var actualText = ""
        val expectedQuery = "Hello there"
        composeTestRule.setContent {
            CandySpaceStackTheme {
                SearchComponent {
                    actualText = it
                }
            }
        }

        val textField = composeTestRule.onNodeWithTag(TEXT_FIELD_TEXT_TAG)
        textField.performClick()
        textField.performTextInput(expectedQuery)
        textField.performImeAction()
        assertEquals(expectedQuery, actualText)
    }

    @Test
    fun `given when SearchComponent composable is displayed, blank spaces are entered and the user performs search from the keyboard, should not invoke the function`() {
        var actualText: String? = null
        val query = "           "
        composeTestRule.setContent {
            CandySpaceStackTheme {
                SearchComponent {
                    actualText = it
                }
            }
        }

        val textField = composeTestRule.onNodeWithTag(TEXT_FIELD_TEXT_TAG)
        textField.performClick()
        textField.performTextInput(query)
        textField.performImeAction()
        assertEquals(null, actualText)
    }

    @Test
    fun `given when SearchComponent composable is displayed, blank spaces are entered and the search is clicked, should not invoke the function`() {
        var actualText: String? = null
        val query = "                       "
        composeTestRule.setContent {
            CandySpaceStackTheme {
                SearchComponent {
                    actualText = it
                }
            }
        }

        val textField = composeTestRule.onNodeWithTag(TEXT_FIELD_TEXT_TAG)
        val searchButton = composeTestRule.onNodeWithTag(SEARCH_BUTTON_TEXT_TAG)
        textField.performClick()
        textField.performTextInput(query)

        searchButton.performClick()

        assertEquals(null, actualText)
    }
}
