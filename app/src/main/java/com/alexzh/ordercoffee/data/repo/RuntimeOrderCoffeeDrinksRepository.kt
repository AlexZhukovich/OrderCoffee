package com.alexzh.ordercoffee.data.repo

import com.alexzh.ordercoffee.data.DummyData
import com.alexzh.ordercoffee.data.model.OrderCoffeeDrink
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map

object RuntimeOrderCoffeeDrinksRepository : OrderCoffeeDrinksRepository {
    private const val MIN_COFFEE_DRINK_VALUE = 0
    private const val MAX_COFFEE_DRINK_VALUE = 99
    private const val DEFAULT_COFFEE_DRINK_COUNT = 0
    private const val INVALID_INDEX = -1

    private val orderCoffeeDrinks = mutableListOf<OrderCoffeeDrink>()

    override suspend fun getAllOrderCoffeeDrinks(): Flow<List<OrderCoffeeDrink>> {
        if (orderCoffeeDrinks.isEmpty()) {
            orderCoffeeDrinks.addAll(DummyData.getAllBasketCoffeeDrinks())
        }
        return flowOf(orderCoffeeDrinks)
    }

    override suspend fun getAddedOrderCoffeeDrinks(): Flow<List<OrderCoffeeDrink>> {
        return getAllOrderCoffeeDrinks()
            .map { orderCoffeeDrinks ->
                orderCoffeeDrinks.filter { orderCoffeeDrink ->
                    orderCoffeeDrink.count != DEFAULT_COFFEE_DRINK_COUNT
                }
            }
    }

    override suspend fun add(coffeeDrinkId: Long): Flow<Boolean> {
        val index = orderCoffeeDrinks.indexOfFirst { it.coffeeDrink.id == coffeeDrinkId }
        val result = if (index != INVALID_INDEX) {
            val orderCoffeeDrink = orderCoffeeDrinks[index]
            val newCountValue = if (orderCoffeeDrink.count == MAX_COFFEE_DRINK_VALUE)
                MAX_COFFEE_DRINK_VALUE
            else
                orderCoffeeDrink.count + 1

            orderCoffeeDrinks[index] = orderCoffeeDrink.copy(coffeeDrink = orderCoffeeDrink.coffeeDrink, count = newCountValue)
            true
        } else {
            false
        }
        return flowOf(result)
    }

    override suspend fun remove(coffeeDrinkId: Long): Flow<Boolean> {
        val index = orderCoffeeDrinks.indexOfFirst { it.coffeeDrink.id == coffeeDrinkId }
        val result = if (index != INVALID_INDEX) {
            val orderCoffeeDrink = orderCoffeeDrinks[index]
            val newCountValue = if (orderCoffeeDrink.count == MIN_COFFEE_DRINK_VALUE)
                MIN_COFFEE_DRINK_VALUE
            else
                orderCoffeeDrink.count - 1

            orderCoffeeDrinks[index] = orderCoffeeDrink.copy(coffeeDrink = orderCoffeeDrink.coffeeDrink, count = newCountValue)
            true
        } else {
            false
        }
        return flowOf(result)
    }

    override suspend fun clear() {
        orderCoffeeDrinks.clear()
    }
}