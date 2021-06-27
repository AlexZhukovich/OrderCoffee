package com.alexzh.ordercoffee.data.model

// TODO: maybe rename `BasketProduct` because it used in CoffeeDrinks and Basket screens
data class BasketProduct(
    val product: Product,
    val count: Int
)