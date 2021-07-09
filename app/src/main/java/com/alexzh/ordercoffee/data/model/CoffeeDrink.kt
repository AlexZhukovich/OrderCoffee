package com.alexzh.ordercoffee.data.model

import android.os.Parcelable
import androidx.annotation.DrawableRes
import kotlinx.parcelize.Parcelize
import java.math.BigDecimal

@Parcelize
data class CoffeeDrink(
    val id: Long,
    val name: String,
    @DrawableRes val image: Int,
    val description: String,
    val size: String,
    val price: BigDecimal
): Parcelable