package com.example.candyspacestack.ui.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.compose.collectAsLazyPagingItems
import coil.annotation.ExperimentalCoilApi
import com.example.candyspacestack.R
import com.example.candyspacestack.ui.composable.SearchComponent
import com.example.candyspacestack.ui.composable.SearchList
import com.example.candyspacestack.ui.model.User
import com.example.candyspacestack.ui.viewmodel.SearchViewModel

@ExperimentalCoilApi
@Composable
fun HomeScreen(onUserClicked: (User) -> Unit) {

    val searchViewModel = hiltViewModel<SearchViewModel>()
    val data = searchViewModel.searchResult.collectAsLazyPagingItems()

    Column(Modifier.padding(16.dp)) {
        Box(modifier = Modifier.fillMaxWidth()) {
            Text(
                modifier = Modifier.padding(16.dp),
                text = stringResource(id = R.string.app_name),
                fontSize = 20.sp,
                fontWeight = FontWeight.Black
            )
        }
        SearchComponent {
            searchViewModel.searchUser(it)
        }
        SearchList(searchResult = data) {
            onUserClicked(it)
        }
    }
}
