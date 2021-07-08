package com.alexzh.ordercoffee.ui.navigation

sealed class Screen(val route: String) {
    object Home : Screen("Home")
    object OrderSummary : Screen("Success")
}