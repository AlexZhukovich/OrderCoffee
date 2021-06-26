package com.alexzh.ordercoffee.model

import androidx.annotation.DrawableRes

data class ProfileItem(
    val id: Long,
    val title: String,
    @DrawableRes val icon: Int
)