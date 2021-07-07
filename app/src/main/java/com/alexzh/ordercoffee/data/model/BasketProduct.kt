package com.alexzh.ordercoffee.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

// TODO: maybe rename `BasketProduct` because it used in CoffeeDrinks and Basket screens
@Parcelize
data class BasketProduct(
    val product: Product,
    val count: Int
) : Parcelable