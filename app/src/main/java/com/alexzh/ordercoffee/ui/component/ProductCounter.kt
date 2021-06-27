package com.alexzh.ordercoffee.ui.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.alexzh.ordercoffee.data.DummyData
import com.alexzh.ordercoffee.data.model.BasketProduct

@Composable
fun ProductCounter(
    basketProduct: BasketProduct,
    onProductIncreased: (Long) -> Unit,
    onProductDecreased: (Long) -> Unit,
    modifier: Modifier = Modifier
) {
    Surface(
        shape = RoundedCornerShape(size = 5.dp),
        border = BorderStroke(1.dp, Color.Gray),
        color = Color.Transparent,
        modifier = Modifier.size(width = 80.dp, height = 30.dp).composed { modifier },
    ) {
        Row(
            horizontalArrangement = Arrangement.Center
        ) {
            Text(
                text = "—",
                fontSize = 22.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier.size(32.dp)
                    .weight(1f)
                    .align(Alignment.CenterVertically)
                    .clickable {
                        onProductDecreased(basketProduct.product.id)
                    }
            )
            Text(
                text = basketProduct.count.toString(),
                modifier = Modifier
                    .weight(1f)
                    .align(Alignment.CenterVertically),
                fontSize = 22.sp,
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = "＋",
                fontSize = 22.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier.size(32.dp)
                    .weight(1f)
                    .align(Alignment.CenterVertically)
                    .clickable {
                        onProductIncreased(basketProduct.product.id)
                    }
            )
        }
    }
}

@Preview
@Composable
fun ProductCounter_Preview() {
    val product = BasketProduct(
        product = DummyData.IRISH_COFFEE,
        count = 42
    )
    ProductCounter(
        basketProduct = product,
        onProductIncreased = { },
        onProductDecreased = { }
    )
}

