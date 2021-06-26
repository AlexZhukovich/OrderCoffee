package com.alexzh.ordercoffee.ui.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.alexzh.ordercoffee.currentRoute
import com.alexzh.ordercoffee.ui.navigation.NavigationItem
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
                    tabs.forEach { navigationItem ->
                        BottomNavigationItem(
                            selected = navController.currentRoute() == navigationItem.route,
                            onClick = {
                                navController.navigate(navigationItem.route)
                            },
                            label = null,
                            icon = {
                                // TODO: fix contentDescription
                                Icon(
                                    painter = painterResource(id = navigationItem.icon),
                                    contentDescription = null
                                )
                            }
                        )
                    }
                }
            }
        ) { innerPadding -> 
            Box(modifier = Modifier.padding(bottom = innerPadding.calculateBottomPadding())) {
                NavHost(navController = navController, startDestination = NavigationItem.CoffeeDrinks.route) {
                    composable(NavigationItem.CoffeeDrinks.route) { CoffeeDrinksScreen(navController) }
                    composable(NavigationItem.Basket.route) { BasketScreen(navController) }
                    composable(NavigationItem.Profile.route) { ProfileScreen(navController) }
                }
            }
        }
    }
}