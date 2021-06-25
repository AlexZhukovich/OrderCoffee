package com.alexzh.ordercoffee.ui.screen

sealed class NavigationItem(val route: String) {
    object CoffeeDrinks : NavigationItem("CoffeeDrinks")
    object Basket : NavigationItem("Basket")
    object Profile : NavigationItem("Profile")
}
