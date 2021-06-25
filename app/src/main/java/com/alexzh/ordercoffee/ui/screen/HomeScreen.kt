package com.alexzh.ordercoffee.ui.screen

import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.alexzh.ordercoffee.ui.theme.OrderCoffeeTheme

@Composable
fun HomeScreen() {
    val tabs = listOf(
        Screen.CoffeeDrinks,
        Screen.Basket,
        Screen.Profile
    )

    val navController = rememberNavController()

    OrderCoffeeTheme {
        Scaffold(
            bottomBar = {
                BottomNavigation {
                    tabs.forEach { screen ->
                        BottomNavigationItem(
                            selected = false,
                            onClick = {
                                navController.navigate(screen.screenName)
                            },
                            label = { Text(screen.screenName) },
                            icon = {  }
                        )
                    }
                }
            }
        ) {
            NavHost(navController = navController, startDestination = Screen.CoffeeDrinks.screenName) {
                composable(Screen.CoffeeDrinks.screenName) { CoffeeDrinksScreen(navController) }
                composable(Screen.Basket.screenName) { BasketScreen(navController) }
                composable(Screen.Profile.screenName) { ProfileScreen(navController) }
            }
        }
    }
}