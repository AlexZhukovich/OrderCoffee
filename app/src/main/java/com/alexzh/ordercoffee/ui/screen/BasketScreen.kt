package com.alexzh.ordercoffee.ui.screen

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavController

@Composable
fun BasketScreen(
    navController: NavController
) {
    Text(Screen.Basket.screenName)
}