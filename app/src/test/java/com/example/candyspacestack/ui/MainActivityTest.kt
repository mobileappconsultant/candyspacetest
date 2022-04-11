package com.example.candyspacestack.ui

import android.os.Build
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onAllNodesWithTag
import androidx.compose.ui.test.onFirst
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performImeAction
import androidx.compose.ui.test.performTextInput
import androidx.test.espresso.IdlingRegistry
import com.example.candyspacestack.CoroutineTestRule
import com.example.candyspacestack.dispatchers.SuccessDispatcher
import com.example.candyspacestack.utils.Constants
import com.example.candyspacestack.utils.Constants.CARD_ITEM_TEXT_TAG
import com.jakewharton.espresso.OkHttp3IdlingResource
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.HiltTestApplication
import java.lang.Thread.sleep
import javax.inject.Inject
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.advanceTimeBy
import kotlinx.coroutines.test.runTest
import okhttp3.OkHttpClient
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
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
class MainActivityTest {

    @get:Rule(order = 0)
    var hiltTestRule = HiltAndroidRule(this)

    @get:Rule(order = 1)
    var composeTestRule = createAndroidComposeRule<MainActivity>()

    @ExperimentalCoroutinesApi
    @get:Rule
    val coroutineTestRule = CoroutineTestRule()

    private val mockWebServer = MockWebServer()

    @Inject
    lateinit var okHttpClient: OkHttpClient

    @Before
    fun setup() {
        mockWebServer.start(8080)

        hiltTestRule.inject()
        IdlingRegistry.getInstance().register(
            (
                AndroidXIdlingResource.asAndroidX(
                    OkHttp3IdlingResource.create(
                        "okhttp",
                        okHttpClient
                    )
                )
                )
        )
    }

    @Test
    fun `given when application is launched and a search is performed, then it should show the list`() =
        runTest {
            mockWebServer.dispatcher = SuccessDispatcher()
            composeTestRule.apply {
                activityRule.scenario.onActivity {
                    val textField = onNodeWithTag(Constants.TEXT_FIELD_TEXT_TAG)
                    textField.performClick()
                    textField.performTextInput("Query")
                    textField.performImeAction()
                    advanceTimeBy(300)
                    sleep(5000)
                    onAllNodesWithTag(CARD_ITEM_TEXT_TAG).onFirst().assertExists()
                }
            }
        }

    @After
    fun teardown() {
        mockWebServer.shutdown()
    }
}
