package com.alexzh.ordercoffee.ui.screen

sealed class Screen(val screenName: String) {
    object CoffeeDrinks : Screen("CoffeeDrinks")
    object Basket : Screen("Basket")
    object Profile : Screen("Profile")
}
