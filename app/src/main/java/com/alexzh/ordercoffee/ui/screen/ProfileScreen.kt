package com.alexzh.ordercoffee.ui.screen

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavController

@Composable
fun ProfileScreen(
    navController: NavController
) {
    Text(Screen.Profile.screenName)
}