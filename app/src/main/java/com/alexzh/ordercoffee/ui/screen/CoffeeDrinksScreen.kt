package com.alexzh.ordercoffee.ui.screen

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavController

@Composable
fun CoffeeDrinksScreen(
    navController: NavController
) {
    Text(Screen.CoffeeDrinks.screenName)
}