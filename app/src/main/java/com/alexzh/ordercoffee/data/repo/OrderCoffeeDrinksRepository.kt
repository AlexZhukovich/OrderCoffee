package com.alexzh.ordercoffee.data.repo

import com.alexzh.ordercoffee.data.model.BasketProduct
import kotlinx.coroutines.flow.Flow

interface OrderCoffeeDrinksRepository {

    suspend fun getAllProducts(): Flow<List<BasketProduct>>

    suspend fun getAddedProducts(): Flow<List<BasketProduct>>

    suspend fun add(productId: Long): Flow<Boolean>

    suspend fun remove(productId: Long): Flow<Boolean>
}