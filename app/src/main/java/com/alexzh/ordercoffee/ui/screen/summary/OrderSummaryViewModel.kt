package com.alexzh.ordercoffee.ui.screen.summary

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alexzh.ordercoffee.data.model.OrderCoffeeDrink
import com.alexzh.ordercoffee.data.repo.OrderCoffeeDrinksRepository
import com.alexzh.ordercoffee.data.repo.RuntimeOrderCoffeeDrinksRepository
import com.alexzh.ordercoffee.ui.common.UiState
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class OrderSummaryViewModel(
    private val repository: OrderCoffeeDrinksRepository = RuntimeOrderCoffeeDrinksRepository
) : ViewModel() {
    private val _uiState: MutableLiveData<UiState<List<OrderCoffeeDrink>>> = MutableLiveData()
    val uiState: LiveData<UiState<List<OrderCoffeeDrink>>>
        get() = _uiState

    fun loadCoffeeDrinks() {
        viewModelScope.launch {
            _uiState.value = UiState.Loading
            repository.getAddedOrderCoffeeDrinks()
                .collect { orderCoffeeDrinks ->
                    _uiState.value = UiState.Success(orderCoffeeDrinks)
                }
        }
    }

    fun clear() {
        viewModelScope.launch {
            repository.clear()
        }
    }
}