package com.alexzh.ordercoffee.ui.screen.basket

import com.alexzh.ordercoffee.data.model.BasketProduct
import java.math.BigDecimal

data class BasketState(
    val products: List<BasketProduct>,
    val totalPrice: BigDecimal
)