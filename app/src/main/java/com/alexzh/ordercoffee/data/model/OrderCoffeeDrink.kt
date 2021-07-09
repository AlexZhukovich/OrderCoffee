package com.alexzh.ordercoffee.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class OrderCoffeeDrink(
    val coffeeDrink: CoffeeDrink,
    val count: Int
) : Parcelable