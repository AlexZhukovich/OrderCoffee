package com.alexzh.ordercoffee.ui.screen

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.alexzh.ordercoffee.data.DummyData
import com.alexzh.ordercoffee.data.model.BasketProduct
import com.alexzh.ordercoffee.ui.component.ProductCounter

@Composable
fun CoffeeDrinksScreen(
    navController: NavController
) {
    val context = LocalContext.current
    val items = DummyData.getAllBasketCoffeeDrinks()

    Column {
        TopAppBar {
            Text(
                text = "Coffee Drinks",
                modifier = Modifier.padding(horizontal = 12.dp),
                fontSize = 18.sp
            )
        }
        CoffeeDrinkList(
            items = items,
            onCoffeeDrink = {
//                Toast.makeText(context, "click on coffee drink with ID: $it", Toast.LENGTH_SHORT).show()
                navController.navigate("CoffeeDrinkDetails/$it")
            },
            onCoffeeDrinkCountIncreased = {
                Toast.makeText(context, "increased coffee drink with ID: $it", Toast.LENGTH_SHORT).show()
            },
            onCoffeeDrinkCountDecreased = {
                Toast.makeText(context, "decreased coffee drink with ID: $it", Toast.LENGTH_SHORT).show()
            }
        )
    }
}

@Composable
fun CoffeeDrinkList(
    items: List<BasketProduct>,
    onCoffeeDrink: (Long) -> Unit,
    onCoffeeDrinkCountIncreased: (Long) -> Unit,
    onCoffeeDrinkCountDecreased: (Long) -> Unit
) {
    LazyColumn(
        modifier = Modifier.padding(horizontal = 16.dp)
    ) {
        items.forEach { item ->
            item {
                CoffeeDrinkItem(
                    basketProduct = item,
                    onCoffeeDrink = onCoffeeDrink,
                    onCoffeeDrinkCountIncreased = onCoffeeDrinkCountIncreased,
                    onCoffeeDrinkCountDecreased = onCoffeeDrinkCountDecreased
                )
                Spacer(modifier = Modifier.height(4.dp))
                Divider()
                Spacer(modifier = Modifier.height(4.dp))
            }
        }
    }
}

@Composable
fun CoffeeDrinkItem(
    basketProduct: BasketProduct,
    onCoffeeDrink: (Long) -> Unit,
    onCoffeeDrinkCountIncreased: (Long) -> Unit,
    onCoffeeDrinkCountDecreased: (Long) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                onCoffeeDrink(basketProduct.product.id)
            }
    ) {
        // TODO: fix contentDescription
        Image(
            painter = painterResource(id = basketProduct.product.image),
            contentDescription = null,
            modifier = Modifier.size(96.dp)
        )
        Column {
            Text(
                text = basketProduct.product.name,
                modifier = Modifier.fillMaxWidth(),
                fontSize = 22.sp
            )
            Text(
                text = basketProduct.product.description,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier.fillMaxWidth()
            )
            Box(
                modifier = Modifier.fillMaxWidth(),
            ) {
                ProductCounter(
                    basketProduct = basketProduct,
                    onProductIncreased = { onCoffeeDrinkCountIncreased(basketProduct.product.id) },
                    onProductDecreased = { onCoffeeDrinkCountDecreased(basketProduct.product.id) },
                    modifier = Modifier.align(Alignment.BottomEnd)
                )
            }
        }
    }
}

@Preview
@Composable
fun CoffeeDrinkItem_Preview() {
    CoffeeDrinkItem(
        basketProduct = BasketProduct(DummyData.ESPRESSO, 3),
        onCoffeeDrink = { },
        onCoffeeDrinkCountIncreased = { },
        onCoffeeDrinkCountDecreased = { }
    )
}

@Preview
@Composable
fun CoffeeDrinksScreen_Preview() {
    CoffeeDrinksScreen(navController = rememberNavController())
}