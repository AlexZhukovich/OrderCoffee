package com.alexzh.ordercoffee.ui.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.alexzh.ordercoffee.model.BasketProduct
import com.alexzh.ordercoffee.model.Product

@Composable
fun ProductCounter(
    basketProduct: BasketProduct,
    onProductIncreased: (Long) -> Unit,
    onProductDecreased: (Long) -> Unit
) {
    Surface(
        shape = RoundedCornerShape(size = 5.dp),
        border = BorderStroke(1.dp, Color.Gray),
        color = Color.Transparent
    ) {
        Row(
            modifier = Modifier.size(width = 120.dp, height = 40.dp),
            horizontalArrangement = Arrangement.Center
        ) {
            Button(
                onClick = { onProductDecreased(basketProduct.product.id) },
                modifier = Modifier.weight(1f)
                    .fillMaxHeight()
            ) {
                Text(
                    text = "-"
                )
            }
            Text(
                text = basketProduct.count.toString(),
                modifier = Modifier.weight(1f)
                    .align(Alignment.CenterVertically),
                fontSize = 24.sp,
                textAlign = TextAlign.Center
            )
            Button(
                onClick = { onProductIncreased(basketProduct.product.id) },
                modifier = Modifier.weight(1f)
                    .fillMaxHeight()
            ) {
                Text(text = "+")
            }
        }
    }
}

@Preview
@Composable
fun ProductCounter_Preview() {
    val product = BasketProduct(
        product = Product(
            id = 42L,
            name = "Test product",
            description = "Description of the 'Test product'"
        ),
        count = 42
    )
    ProductCounter(
        basketProduct = product,
        onProductIncreased = { },
        onProductDecreased = { }
    )
}

