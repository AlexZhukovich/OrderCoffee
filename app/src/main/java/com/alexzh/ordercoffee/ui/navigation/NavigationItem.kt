package com.alexzh.ordercoffee.ui.navigation

import androidx.annotation.DrawableRes
import com.alexzh.ordercoffee.R

sealed class NavigationItem(val route: String, val routesIncluded: List<String>, @DrawableRes val icon: Int) {
    object CoffeeDrinks : NavigationItem("CoffeeDrinks", listOf("CoffeeDrinkDetails/{coffeeDrinkId}"), R.drawable.ic_coffee)
    object CoffeeDrinkDetails: NavigationItem("CoffeeDrinkDetails/{coffeeDrinkId}", emptyList<String>(), R.drawable.ic_coffee)
    object Basket : NavigationItem("Basket",  emptyList<String>(), R.drawable.ic_basket)
    object Profile : NavigationItem("Profile",  emptyList<String>(), R.drawable.ic_profile)
}
