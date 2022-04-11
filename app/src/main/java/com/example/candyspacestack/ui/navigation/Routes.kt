package com.example.candyspacestack.ui.navigation

sealed class Routes(val route: String) {
    object Home : Routes("homeScreen")
    object Details : Routes("detailsScreen/{user}") {
        fun createRoute(item: String) = "detailsScreen/$item"
    }
}
