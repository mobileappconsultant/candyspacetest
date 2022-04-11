package com.example.candyspacestack.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import coil.annotation.ExperimentalCoilApi
import com.example.candyspacestack.ui.viewmodel.SearchViewModel
import com.example.candyspacestack.ui.theme.CandySpaceStackTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val searchViewModel by viewModels<SearchViewModel>()

    @OptIn(ExperimentalCoilApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CandySpaceStackTheme {
            }
        }
    }
}
