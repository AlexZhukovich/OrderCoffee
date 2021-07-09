package com.alexzh.ordercoffee.ui.screen.basket

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Button
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.alexzh.ordercoffee.data.DummyData
import com.alexzh.ordercoffee.data.model.OrderCoffeeDrink
import com.alexzh.ordercoffee.ui.common.UiState
import com.alexzh.ordercoffee.ui.component.ProductCounter
import java.math.BigDecimal

@Composable
fun BasketScreen(
    navigateToSuccess: () -> Unit,
    viewModel: BasketViewModel = BasketViewModel()
) {
    viewModel.loadCoffeeDrinks()
    viewModel.uiState.observeAsState(initial = UiState.Loading).value.let { uiState ->
        when (uiState) {
            is UiState.Loading -> { }
            is UiState.Success -> {
                BasketSuccessScreen(
                    uiState.data,
                    navigateToSuccess,
                    addCoffeeDrink = { viewModel.addCoffeeDrink(it) },
                    removeCoffeeDrink = { viewModel.removeCoffeeDrink(it) }
                )
            }
            is UiState.Error -> { }
        }
    }
}

@Composable
fun BasketSuccessScreen(
    state: BasketState,
    navigateToSuccess: () -> Unit,
    addCoffeeDrink: (Long) -> Unit,
    removeCoffeeDrink: (Long) -> Unit
) {
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        TopAppBar {
            Text(
                text = "Basket",
                modifier = Modifier.padding(horizontal = 12.dp),
                fontSize = 18.sp
            )
        }
        PaymentInfo(
            deliveryCosts = BigDecimal(5),
            total = state.totalPrice,
            currency = '€',
            isPayButtonEnabled = state.orderCoffeeDrinks.isNotEmpty(),
            onPayed = navigateToSuccess
        )
        Spacer(modifier = Modifier.height(8.dp))
        CoffeeDrinkList(
            orderCoffeeDrinks = state.orderCoffeeDrinks,
            onCoffeeDrinkCountIncreased = removeCoffeeDrink,
            onCoffeeDrinkCountDecreased = addCoffeeDrink
        )
    }
}

@Composable
private fun PaymentInfo(
    deliveryCosts: BigDecimal,
    total: BigDecimal,
    currency: Char,
    isPayButtonEnabled: Boolean,
    onPayed: () -> Unit
) {
    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        PaymentInfoItem(name = "Delivery Costs:", value = deliveryCosts, currency = currency)
        PaymentInfoItem(name = "Total:", value = total, currency = currency)
        PayButton(isButtonEnabled = isPayButtonEnabled, onPayed = onPayed)
    }
}

@Composable
private fun PaymentInfoItem(
    name: String,
    value: BigDecimal,
    currency: Char
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp)
    ) {
        Text(
            text = name,
            modifier = Modifier
                .fillMaxWidth()
                .weight(4f),
            fontSize = 16.sp
        )

        Text(
            text = "$currency $value",
            modifier = Modifier
                .fillMaxWidth()
                .weight(2f),
            textAlign = TextAlign.End,
            fontSize = 16.sp
        )
    }
}

@Composable
private fun PayButton(
    isButtonEnabled: Boolean,
    onPayed: () -> Unit
) {
    Button(
        enabled = isButtonEnabled,
        onClick = { onPayed() },
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Text(
            text = "Pay",
            fontSize = 24.sp
        )
    }
}

@Composable
private fun CoffeeDrinkList(
    orderCoffeeDrinks: List<OrderCoffeeDrink>,
    onCoffeeDrinkCountIncreased: (Long) -> Unit,
    onCoffeeDrinkCountDecreased: (Long) -> Unit
) {
    LazyColumn {
        orderCoffeeDrinks.forEach { item ->
            item {
                CoffeeDrinkItem(
                    orderCoffeeDrink = item,
                    onCoffeeDrinkCountIncreased = onCoffeeDrinkCountIncreased,
                    onCoffeeDrinkCountDecreased = onCoffeeDrinkCountDecreased
                )
                Divider()
            }
        }
    }
}

@Composable
private fun CoffeeDrinkItem(
    orderCoffeeDrink: OrderCoffeeDrink,
    onCoffeeDrinkCountIncreased: (Long) -> Unit,
    onCoffeeDrinkCountDecreased: (Long) -> Unit
) {
    Row(
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
                .weight(1.0f)
        ) {
            Text(
                text = orderCoffeeDrink.coffeeDrink.name,
                fontSize = 18.sp,
                modifier = Modifier.fillMaxWidth()
            )
            Text(
                text = orderCoffeeDrink.coffeeDrink.description,
                fontSize = 14.sp,
                maxLines = 3,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 4.dp)
            )
        }
        Box(
            modifier = Modifier
                .fillMaxHeight()
                .padding(8.dp)
        ) {
            ProductCounter(
                orderCoffeeDrink = orderCoffeeDrink,
                onProductIncreased = onCoffeeDrinkCountIncreased,
                onProductDecreased = onCoffeeDrinkCountDecreased
            )
        }
    }

}

@Preview
@Composable
private fun CoffeeDrinkItem_Preview() {
    val orderCoffeeDrink = OrderCoffeeDrink(
        coffeeDrink = DummyData.AMERICANO,
        count = 42
    )
    CoffeeDrinkItem(
        orderCoffeeDrink = orderCoffeeDrink,
        onCoffeeDrinkCountIncreased = { },
        onCoffeeDrinkCountDecreased = { }
    )
}

@Preview
@Composable
private fun PaymentInfoItem_Preview() {
    PaymentInfoItem(
        name = "Total",
        value = BigDecimal(42),
        currency = '€'
    )
}

@Preview
@Composable
private fun PaymentInfo_Preview() {
    PaymentInfo(
        deliveryCosts = BigDecimal(5),
        total = BigDecimal(42),
        currency = '$',
        isPayButtonEnabled = true,
        onPayed = { }
    )
}

@Preview
@Composable
private fun BasketScreen_Preview() {
    BasketScreen(
        navigateToSuccess = { }
    )
}