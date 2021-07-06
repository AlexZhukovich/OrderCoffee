package com.alexzh.ordercoffee

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.alexzh.ordercoffee.navigation.createRouter
import com.alexzh.ordercoffee.ui.navigation.Screen
import com.alexzh.ordercoffee.ui.screen.HomeScreen
import com.alexzh.ordercoffee.ui.screen.SuccessScreen
import com.alexzh.ordercoffee.ui.theme.OrderCoffeeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            OrderCoffeeTheme {
                val navController = rememberNavController()

                NavHost(navController = navController, startDestination = Screen.Home.route) {
                    composable(Screen.Home.route) {
                        HomeScreen(
                            createRouter { route ->
                                navController.navigate(route)
                            }
                        )
                    }

                    composable(Screen.Success.route) {
                        SuccessScreen(navController)
                    }
                }
            }
        }
    }
}