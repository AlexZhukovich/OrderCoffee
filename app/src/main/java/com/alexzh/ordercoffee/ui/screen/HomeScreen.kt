package com.alexzh.ordercoffee.ui.screen

import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.alexzh.ordercoffee.currentRoute
import com.alexzh.ordercoffee.ui.theme.OrderCoffeeTheme

@Composable
fun HomeScreen() {
    val tabs = listOf(
        NavigationItem.CoffeeDrinks,
        NavigationItem.Basket,
        NavigationItem.Profile
    )

    val navController = rememberNavController()

    OrderCoffeeTheme {
        Scaffold(
            bottomBar = {
                BottomNavigation {
                    tabs.forEach { screen ->
                        BottomNavigationItem(
                            selected = navController.currentRoute() == screen.route,
                            onClick = {
                                navController.navigate(screen.route)
                            },
                            label = { Text(screen.route) },
                            icon = {  }
                        )
                    }
                }
            }
        ) {
            NavHost(navController = navController, startDestination = NavigationItem.CoffeeDrinks.route) {
                composable(NavigationItem.CoffeeDrinks.route) { CoffeeDrinksScreen(navController) }
                composable(NavigationItem.Basket.route) { BasketScreen(navController) }
                composable(NavigationItem.Profile.route) { ProfileScreen(navController) }
            }
        }
    }
}