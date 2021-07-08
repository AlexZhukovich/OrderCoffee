package com.alexzh.ordercoffee.ui.screen.summary

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alexzh.ordercoffee.data.model.BasketProduct
import com.alexzh.ordercoffee.data.repo.OrderCoffeeDrinksRepository
import com.alexzh.ordercoffee.data.repo.RuntimeOrderCoffeeDrinksRepository
import com.alexzh.ordercoffee.ui.common.UiState
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class OrderSummaryViewModel(
    private val repository: OrderCoffeeDrinksRepository = RuntimeOrderCoffeeDrinksRepository
) : ViewModel() {
    private val _uiState: MutableLiveData<UiState<List<BasketProduct>>> = MutableLiveData()
    val uiState: LiveData<UiState<List<BasketProduct>>>
        get() = _uiState

    fun loadCoffeeDrinks() {
        viewModelScope.launch {
            _uiState.value = UiState.Loading
            repository.getAddedProducts()
                .collect { products ->
                    _uiState.value = UiState.Success(products)
                }
        }
    }

    fun clear() {
        viewModelScope.launch {
            repository.clear()
        }
    }
}