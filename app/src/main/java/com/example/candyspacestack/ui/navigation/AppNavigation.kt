package com.example.candyspacestack.ui.navigation

import android.net.Uri
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import coil.annotation.ExperimentalCoilApi
import com.example.candyspacestack.ui.model.User
import com.example.candyspacestack.ui.screens.DetailsScreen
import com.example.candyspacestack.ui.screens.HomeScreen
import com.google.gson.Gson

@Composable
@ExperimentalCoilApi
fun AppNavigation() {
    val navController = rememberNavController()
    NavHost(navController, startDestination = Routes.Home.route) {
        composable(route = Routes.Home.route) {
            HomeScreen {
                val json = Uri.encode(Gson().toJson(it))
                navController.navigate(Routes.Details.createRoute(json))
            }
        }

        composable(
            route = Routes.Details.route,
            arguments = listOf(
                navArgument("user") {
                    type = AssetParamType()
                }
            )
        ) {
            val item = it.arguments?.getParcelable<User>("user")

            requireNotNull(item) { "Item not found" }

            DetailsScreen(item) {
                navController.popBackStack()
            }
        }
    }
}
