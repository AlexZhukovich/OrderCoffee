package com.alexzh.ordercoffee.ui.screen.basket

import com.alexzh.ordercoffee.data.model.OrderCoffeeDrink
import java.math.BigDecimal

data class BasketState(
    val orderCoffeeDrinks: List<OrderCoffeeDrink>,
    val totalPrice: BigDecimal
)