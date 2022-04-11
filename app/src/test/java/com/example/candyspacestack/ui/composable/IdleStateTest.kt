package com.example.candyspacestack.ui.composable

import androidx.compose.ui.Modifier
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertTextContains
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import com.example.candyspacestack.ui.theme.CandySpaceStackTheme
import com.example.candyspacestack.utils.Constants
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
class IdleStateTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun `given when ErrorPage composable is displayed, it should display the correct text`() {
        composeTestRule.setContent {
            CandySpaceStackTheme {
                IdleState(modifier = Modifier)
            }
        }

        composeTestRule.onNodeWithTag(Constants.IDLE_TAG).apply {
            assertIsDisplayed()
            assertTextContains("Search for stackoverflow users")
        }
    }
}
