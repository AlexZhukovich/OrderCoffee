package com.alexzh.ordercoffee.data.repo

import com.alexzh.ordercoffee.data.DummyData
import com.alexzh.ordercoffee.data.model.BasketProduct
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map

object RuntimeOrderCoffeeDrinksRepository : OrderCoffeeDrinksRepository {
    private const val MIN_PRODUCT_VALUE = 0
    private const val MAX_PRODUCT_VALUE = 99
    private const val DEFAULT_PRODUCT_COUNT = 0
    private const val INVALID_INDEX = -1

    private val products = mutableListOf<BasketProduct>()

    override suspend fun getAllProducts(): Flow<List<BasketProduct>> {
        if (products.isEmpty()) {
            products.addAll(DummyData.getAllBasketCoffeeDrinks())
        }
        return flowOf(products)
    }

    override suspend fun getAddedProducts(): Flow<List<BasketProduct>> {
        return getAllProducts()
            .map { products ->
                products.filter { product ->
                    product.count != DEFAULT_PRODUCT_COUNT
                }
            }
    }

    override suspend fun add(productId: Long): Flow<Boolean> {
        val index = products.indexOfFirst { it.product.id == productId }
        val result = if (index != INVALID_INDEX) {
            val basketProduct = products[index]
            val newCountValue = if (basketProduct.count == MAX_PRODUCT_VALUE)
                MAX_PRODUCT_VALUE
            else
                basketProduct.count + 1

            products[index] = basketProduct.copy(product = basketProduct.product, count = newCountValue)
            true
        } else {
            false
        }
        return flowOf(result)
    }

    override suspend fun remove(productId: Long): Flow<Boolean> {
        val index = products.indexOfFirst { it.product.id == productId }
        val result = if (index != INVALID_INDEX) {
            val basketProduct = products[index]
            val newCountValue = if (basketProduct.count == MIN_PRODUCT_VALUE)
                MIN_PRODUCT_VALUE
            else
                basketProduct.count - 1

            products[index] = basketProduct.copy(product = basketProduct.product, count = newCountValue)
            true
        } else {
            false
        }
        return flowOf(result)
    }
}