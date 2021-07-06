package com.alexzh.ordercoffee.ui.screen.basket

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alexzh.ordercoffee.data.repo.OrderCoffeeDrinksRepository
import com.alexzh.ordercoffee.data.repo.RuntimeOrderCoffeeDrinksRepository
import com.alexzh.ordercoffee.ui.common.UiState
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import java.math.BigDecimal

class BasketViewModel(
    private val repository: OrderCoffeeDrinksRepository = RuntimeOrderCoffeeDrinksRepository
) : ViewModel() {
    private val _uiState: MutableLiveData<UiState<BasketState>> = MutableLiveData()
    val uiState: LiveData<UiState<BasketState>>
        get() = _uiState

    fun loadCoffeeDrinks() {
        viewModelScope.launch {
            _uiState.value = UiState.Loading
            repository.getAddedProducts()
                .collect { products ->
                    val totalPrice: BigDecimal = products.sumOf { it.product.price.multiply(BigDecimal(it.count)) }
                    _uiState.value = UiState.Success(BasketState(products, totalPrice))
                }
        }
    }

    fun addCoffeeDrink(coffeeDrinkId: Long) {
        viewModelScope.launch {
            repository.add(coffeeDrinkId)
                .collect { isAdded ->
                    if (isAdded) {
                        loadCoffeeDrinks()
                    }
                }
        }
    }

    fun removeCoffeeDrink(coffeeDrinkId: Long) {
        viewModelScope.launch {
            repository.remove(coffeeDrinkId)
                .collect { isRemoved ->
                    if (isRemoved) {
                        loadCoffeeDrinks()
                    }
                }
        }
    }
}