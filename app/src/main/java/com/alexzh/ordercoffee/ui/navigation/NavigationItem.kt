package com.alexzh.ordercoffee.ui.navigation

import androidx.annotation.DrawableRes
import com.alexzh.ordercoffee.R

sealed class NavigationItem(val route: String, @DrawableRes val icon: Int) {
    object CoffeeDrinks : NavigationItem("CoffeeDrinks", R.drawable.ic_coffee)
    object Basket : NavigationItem("Basket", R.drawable.ic_basket)
    object Profile : NavigationItem("Profile", R.drawable.ic_profile)
}
