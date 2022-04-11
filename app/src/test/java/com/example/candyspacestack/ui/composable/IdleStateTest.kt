package com.example.candyspacestack.ui.composable

import android.os.Build
import androidx.compose.ui.Modifier
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertTextContains
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithTag
import com.example.candyspacestack.CoroutineTestRule
import com.example.candyspacestack.ui.MainActivity
import com.example.candyspacestack.ui.theme.CandySpaceStackTheme
import com.example.candyspacestack.utils.Constants
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.HiltTestApplication
import kotlinx.coroutines.ExperimentalCoroutinesApi
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
class IdleStateTest {
    @get:Rule(order = 0)
    var hiltTestRule = HiltAndroidRule(this)

    @get:Rule(order = 1)
    var composeTestRule = createAndroidComposeRule<MainActivity>()

    @ExperimentalCoroutinesApi
    @get:Rule
    val coroutineTestRule = CoroutineTestRule()

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
