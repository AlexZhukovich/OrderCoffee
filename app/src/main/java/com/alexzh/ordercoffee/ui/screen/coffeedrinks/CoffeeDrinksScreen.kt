package com.alexzh.ordercoffee.ui.screen.coffeedrinks

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.alexzh.ordercoffee.data.DummyData
import com.alexzh.ordercoffee.data.model.OrderCoffeeDrink
import com.alexzh.ordercoffee.ui.common.UiState
import com.alexzh.ordercoffee.ui.component.ProductCounter

@Composable
fun CoffeeDrinksScreen(
    navigateToCoffeeDrinkDetails: (Long) -> Unit,
    viewModel: CoffeeDrinksViewModel = CoffeeDrinksViewModel()
) {
    viewModel.uiState.observeAsState(initial = UiState.Loading).value.let { uiState ->
        when (uiState) {
            is UiState.Loading -> {
                viewModel.loadCoffeeDrinks()
            }
            is UiState.Success -> {
                Column {
                    TopAppBar {
                        Text(
                            text = "Coffee Drinks",
                            modifier = Modifier.padding(horizontal = 12.dp),
                            fontSize = 18.sp
                        )
                    }
                    CoffeeDrinkList(
                        items = uiState.data,
                        onCoffeeDrink = navigateToCoffeeDrinkDetails,
                        onCoffeeDrinkCountIncreased = {
                              viewModel.addCoffeeDrink(it)
                        },
                        onCoffeeDrinkCountDecreased = {
                            viewModel.removeCoffeeDrink(it)
                        }
                    )
                }
            }
            is UiState.Error -> {}
        }
    }
}

@Composable
fun CoffeeDrinkList(
    items: List<OrderCoffeeDrink>,
    onCoffeeDrink: (Long) -> Unit,
    onCoffeeDrinkCountIncreased: (Long) -> Unit,
    onCoffeeDrinkCountDecreased: (Long) -> Unit
) {
    LazyColumn {
        items.forEach { item ->
            item {
                CoffeeDrinkItem(
                    orderCoffeeDrink = item,
                    onCoffeeDrink = onCoffeeDrink,
                    onCoffeeDrinkCountIncreased = onCoffeeDrinkCountIncreased,
                    onCoffeeDrinkCountDecreased = onCoffeeDrinkCountDecreased
                )
                Divider()
            }
        }
    }
}

@Composable
fun CoffeeDrinkItem(
    orderCoffeeDrink: OrderCoffeeDrink,
    onCoffeeDrink: (Long) -> Unit,
    onCoffeeDrinkCountIncreased: (Long) -> Unit,
    onCoffeeDrinkCountDecreased: (Long) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                onCoffeeDrink(orderCoffeeDrink.coffeeDrink.id)
            }

    ) {
        // TODO: fix contentDescription
        Image(
            painter = painterResource(id = orderCoffeeDrink.coffeeDrink.image),
            contentDescription = null,
            modifier = Modifier
                .size(96.dp)
                .padding(start = 8.dp)
        )
        Column(
            modifier = Modifier
                .weight(1.0f)
                .padding(top = 4.dp)
        ) {
            Text(
                text = orderCoffeeDrink.coffeeDrink.name,
                modifier = Modifier.fillMaxWidth(),
                fontSize = 22.sp
            )
            Text(
                text = orderCoffeeDrink.coffeeDrink.description,
                maxLines = 3,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier.fillMaxWidth()
            )
        }
        Box(
            modifier = Modifier
                .fillMaxHeight()
                .padding(start = 8.dp, top = 8.dp, end = 16.dp, bottom = 8.dp)
        ) {
            ProductCounter(
                orderCoffeeDrink = orderCoffeeDrink,
                onProductIncreased = { onCoffeeDrinkCountIncreased(orderCoffeeDrink.coffeeDrink.id) },
                onProductDecreased = { onCoffeeDrinkCountDecreased(orderCoffeeDrink.coffeeDrink.id) }
            )
        }
    }
}

@Preview
@Composable
fun CoffeeDrinkItem_Preview() {
    CoffeeDrinkItem(
        orderCoffeeDrink = OrderCoffeeDrink(DummyData.ESPRESSO, 3),
        onCoffeeDrink = { },
        onCoffeeDrinkCountIncreased = { },
        onCoffeeDrinkCountDecreased = { }
    )
}

@Preview
@Composable
fun CoffeeDrinksScreen_Preview() {
    CoffeeDrinksScreen(
        navigateToCoffeeDrinkDetails = { }
    )
}