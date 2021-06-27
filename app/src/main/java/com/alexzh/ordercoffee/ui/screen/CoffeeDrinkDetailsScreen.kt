package com.alexzh.ordercoffee.ui.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.alexzh.ordercoffee.data.DummyData

@Composable
fun CoffeeDrinkDetailsScreen(
    navController: NavController,
    coffeeDrinkId: Long
) {
    val basketProduct = requireNotNull(DummyData.findBasketCoffeeDrink(coffeeDrinkId))

    Box(modifier = Modifier.fillMaxSize()) {
        Text(
            text = basketProduct.product.name,
            fontSize = 42.sp,
            modifier = Modifier.align(Alignment.Center)
        )
    }
}