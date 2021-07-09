package com.alexzh.ordercoffee.data.repo

import com.alexzh.ordercoffee.data.model.OrderCoffeeDrink
import kotlinx.coroutines.flow.Flow

interface OrderCoffeeDrinksRepository {

    suspend fun getAllOrderCoffeeDrinks(): Flow<List<OrderCoffeeDrink>>

    suspend fun getAddedOrderCoffeeDrinks(): Flow<List<OrderCoffeeDrink>>

    suspend fun add(coffeeDrinkId: Long): Flow<Boolean>

    suspend fun remove(coffeeDrinkId: Long): Flow<Boolean>

    suspend fun clear()
}