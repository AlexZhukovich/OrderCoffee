package com.alexzh.ordercoffee.ui.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import androidx.navigation.navDeepLink
import com.alexzh.ordercoffee.navigation.Router
import com.alexzh.ordercoffee.navigation.currentRoute
import com.alexzh.ordercoffee.ui.navigation.NavigationItem
import com.alexzh.ordercoffee.ui.navigation.Screen
import com.alexzh.ordercoffee.ui.screen.basket.BasketScreen
import com.alexzh.ordercoffee.ui.screen.coffeedrinks.CoffeeDrinksScreen
import com.alexzh.ordercoffee.ui.theme.OrderCoffeeTheme

@Composable
fun HomeScreen(
    externalRouter: Router
) {
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
                        val currentRoute = navController.currentRoute()

                        BottomNavigationItem(
                            selected = currentRoute == navigationItem.route || navigationItem.routesIncluded.contains(currentRoute),
                            onClick = {
                                if (currentRoute != navigationItem.route) {
                                    navController.navigate(navigationItem.route)
                                }
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
                val uri = "https://example.com"

                NavHost(navController = navController, startDestination = NavigationItem.CoffeeDrinks.route) {
                    composable(NavigationItem.CoffeeDrinks.route) {
                        CoffeeDrinksScreen(
                            navigateToCoffeeDrinkDetails = {
                                navController.navigate(NavigationItem.CoffeeDrinkDetails.createRoute(it))
                            }
                        )
                    }

                    //   adb shell am start -W -a android.intent.action.VIEW -d "https://example.com/CoffeeDrinkDetails/coffeeDrinkId=3"
                    composable(
                        route = NavigationItem.CoffeeDrinkDetails.route,
                        arguments = listOf(navArgument("coffeeDrinkId") { type = NavType.LongType } ),
                        deepLinks = listOf(navDeepLink { uriPattern = "$uri/CoffeeDrinkDetails/coffeeDrinkId={coffeeDrinkId}" })
                    ) {
                        CoffeeDrinkDetailsScreen(
                            navController = navController,
                            coffeeDrinkId = it.arguments?.getLong("coffeeDrinkId") ?: -1L
                        )
                    }

                    composable(NavigationItem.Basket.route) {
                        BasketScreen(
                            navigateToSuccess = { externalRouter.navigateTo(Screen.OrderSummary.route) }
                        )
                    }

                    composable(NavigationItem.Profile.route) {
                        ProfileScreen()
                    }
                }
            }
        }
    }
}