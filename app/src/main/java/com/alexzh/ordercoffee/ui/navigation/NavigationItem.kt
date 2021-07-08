package com.alexzh.ordercoffee.ui.navigation

import androidx.annotation.DrawableRes
import com.alexzh.ordercoffee.R

const val COFFEE_DRINKS_KEY = "CoffeeDrinks"
const val COFFEE_DRINK_DETAILS_KEY = "CoffeeDrinkDetails"
const val BASKET_KEY = "Basket"
const val PROFILE_KEY = "Profile"

sealed class NavigationItem(val route: String, val routesIncluded: List<String>, @DrawableRes val icon: Int) {
    object CoffeeDrinks : NavigationItem(COFFEE_DRINKS_KEY, listOf("$COFFEE_DRINK_DETAILS_KEY/{coffeeDrinkId}"), R.drawable.ic_coffee)
    object CoffeeDrinkDetails: NavigationItem("$COFFEE_DRINK_DETAILS_KEY/{coffeeDrinkId}", emptyList(), R.drawable.ic_coffee) {
        fun createRoute(coffeeDrinkId: Long) = "$COFFEE_DRINK_DETAILS_KEY/$coffeeDrinkId"
    }
    object Basket : NavigationItem(BASKET_KEY,  emptyList(), R.drawable.ic_basket)
    object Profile : NavigationItem(PROFILE_KEY,  emptyList(), R.drawable.ic_profile)
}
