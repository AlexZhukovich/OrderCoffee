package com.alexzh.ordercoffee.ui.screen

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.alexzh.ordercoffee.model.BasketProduct
import com.alexzh.ordercoffee.model.Product
import com.alexzh.ordercoffee.ui.component.ProductCounter
import java.math.BigDecimal

@Composable
fun BasketScreen(
    navController: NavController
) {

    val context = LocalContext.current
    val items = listOf(
        BasketProduct(
            Product(1L, "Test product 1", "Description of the 'Test product 1'"),
            5
        ),
        BasketProduct(
            Product(2L, "Test product 2", "Description of the 'Test product 2'"),
            4
        ),
        BasketProduct(
            Product(3L, "Test product 3", "Description of the 'Test product 3'"),
            3
        ),
        BasketProduct(
            Product(4L, "Test product 4", "Description of the 'Test product 4'"),
            2
        ),
        BasketProduct(
            Product(5L, "Test product 5", "Description of the 'Test product 5'"),
            1
        ),
    )

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        TopAppBar {
            Text(
                text = "Coffee Drinks",
                modifier = Modifier.padding(horizontal = 12.dp),
                fontSize = 18.sp
            )
        }
        PaymentInfo(
            deliveryCosts = BigDecimal(5),
            total = BigDecimal(120),
            currency = '€',
            onPayed = {
                Toast.makeText(context, "Pay -> clicked", Toast.LENGTH_SHORT).show()
            }
        )
        Spacer(modifier = Modifier.height(8.dp))
        ProductList(
            basketProducts = items,
            onProductIncreased = { },
            onProductDecreased = { }
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
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Column(
            modifier = Modifier.weight(4f)
        ) {
            Text(
                text = basketProduct.product.name,
                fontSize = 18.sp
            )
            Text(
                text = basketProduct.product.description,
                fontSize = 14.sp
            )
        }

        ProductCounter(
            basketProduct = basketProduct,
            onProductIncreased = onProductIncreased,
            onProductDecreased = onProductDecreased
        )
    }
}

@Preview
@Composable
private fun ProductItem_Preview() {
    val product = BasketProduct(
        product = Product(
            id = 42L,
            name = "Test product",
            description = "Description of the 'Test product'"
        ),
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
    BasketScreen(rememberNavController())
}