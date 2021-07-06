package com.alexzh.ordercoffee.ui.screen

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Button
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.alexzh.ordercoffee.data.DummyData
import com.alexzh.ordercoffee.data.model.BasketProduct
import com.alexzh.ordercoffee.ui.component.ProductCounter
import com.alexzh.ordercoffee.ui.navigation.Screen
import java.math.BigDecimal

@Composable
fun BasketScreen(
    rootNavController: NavController,
    tabsNavController: NavController
) {
    val context = LocalContext.current
    val items = DummyData.getBasketProducts()

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
            total = BigDecimal(120),
            currency = '€',
            onPayed = {
                rootNavController.navigate(Screen.Success.route)
            }
        )
        Spacer(modifier = Modifier.height(8.dp))
        ProductList(
            basketProducts = items,
            onProductIncreased = {
                Toast.makeText(context, "onProductIncreased with ID: $it", Toast.LENGTH_SHORT).show()
            },
            onProductDecreased = {
                Toast.makeText(context, "onProductDecreased with ID: $it", Toast.LENGTH_SHORT).show()
            }
        )
    }
}

@Composable
private fun PaymentInfo(
    deliveryCosts: BigDecimal,
    total: BigDecimal,
    currency: Char,
    onPayed: () -> Unit
) {
    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        PaymentInfoItem(name = "Delivery Costs:", value = deliveryCosts, currency = currency)
        PaymentInfoItem(name = "Total:", value = total, currency = currency)
        PayButton(onPayed = onPayed)
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
    onPayed: () -> Unit
) {
    Button(
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
private fun ProductList(
    basketProducts: List<BasketProduct>,
    onProductIncreased: (Long) -> Unit,
    onProductDecreased: (Long) -> Unit
) {
    LazyColumn {
        basketProducts.forEach { item ->
            item {
                ProductItem(
                    basketProduct = item,
                    onProductIncreased = onProductIncreased,
                    onProductDecreased = onProductDecreased
                )
                Divider()
            }
        }
    }
}

@Composable
private fun ProductItem(
    basketProduct: BasketProduct,
    onProductIncreased: (Long) -> Unit,
    onProductDecreased: (Long) -> Unit
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
                text = basketProduct.product.name,
                fontSize = 18.sp,
                modifier = Modifier.fillMaxWidth()
            )
            Text(
                text = basketProduct.product.description,
                fontSize = 14.sp,
                maxLines = 3,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier.fillMaxWidth()
                        .padding(top = 4.dp)
            )
        }
        Box(
            modifier = Modifier
                .fillMaxHeight()
                .padding(start = 8.dp, top = 8.dp, end = 16.dp, bottom = 8.dp)
        ) {
            ProductCounter(
                basketProduct = basketProduct,
                onProductIncreased = onProductIncreased,
                onProductDecreased = onProductDecreased
            )
        }
    }

}

@Preview
@Composable
private fun ProductItem_Preview() {
    val product = BasketProduct(
        product = DummyData.AMERICANO,
        count = 42
    )
    ProductItem(
        basketProduct = product,
        onProductIncreased = { },
        onProductDecreased = { }
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
        onPayed = { }
    )
}

@Preview
@Composable
private fun BasketScreen_Preview() {
    BasketScreen(
        rememberNavController(),
        rememberNavController()
    )
}