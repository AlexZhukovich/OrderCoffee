package com.alexzh.ordercoffee.data.model

import androidx.annotation.DrawableRes
import java.math.BigDecimal

data class Product(
    val id: Long,
    val name: String,
    @DrawableRes val image: Int,
    val description: String,
    val size: String,
    val price: BigDecimal
)