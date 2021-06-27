package com.alexzh.ordercoffee.data

import com.alexzh.ordercoffee.R
import com.alexzh.ordercoffee.data.model.BasketProduct
import com.alexzh.ordercoffee.data.model.Product
import java.math.BigDecimal

object DummyData {
    val AMERICANO = Product(
        id = 1L,
        name = "Americano",
        image = R.drawable.americano_small,
        description = "Americano is a type of coffee drink prepared by diluting an espresso with hot water, giving it a similar strength to, but different flavour from, traditionally brewed coffee. ",
        size = "150 ml",
        price = BigDecimal(6.5)
    )

    val CAPPUCCINO = Product(
        id = 2L,
        name = "Cappuccino",
        image = R.drawable.cappuccino_small,
        description = "A cappuccino is an espresso-based coffee drink that originated in Italy, and is traditionally prepared with steamed milk foam.",
        size = "250 ml",
        price = BigDecimal(6.0)
    )

    val ESPRESSO = Product(
        id = 3L,
        name = "Espresso",
        image = R.drawable.espresso_small,
        description = "Espresso is coffee of Italian origin, brewed by forcing a small amount of nearly boiling water under pressure (expressing) through finely-ground coffee beans.",
        size = "200 ml",
        price = BigDecimal(5.0)
    )

    val ESPRESSO_MACCHIATO = Product(
        id = 4L,
        name = "Espresso Macchiato",
        image = R.drawable.espresso_macchiato_small,
        description = "Espresso Macchiato is a coffee beverage (a single or double espresso topped with a dollop of heated, foamed milk).",
        size = "300 ml",
        price = BigDecimal(6.5)
    )

    val FRAPPINO = Product(
        id = 5L,
        name = "Frappino",
        image = R.drawable.frappino_small,
        description = "Frappino is a blended coffee drinks. It consists of coffee base, blended with ice and other various ingredients, usually topped with whipped cream.",
        size = "400 ml",
        price = BigDecimal(6.0)
    )

    val ICED_MOCHA = Product(
        id = 6L,
        name = "Iced Mocha",
        image = R.drawable.iced_mocha_small,
        description = "Iced Mocha is a coffee beverage. It based on Espresso and chocolate syrup with cold milk, foam and whipped cream or ice cream.",
        size = "400 ml",
        price = BigDecimal(6.5)
    )

    val IRISH_COFFEE = Product(
        id = 7L,
        name = "Irish coffee",
        image = R.drawable.irish_coffee_small,
        description = "Irish coffee is a cocktail consisting of hot coffee, Irish whiskey, and sugar stirred, and topped with cream.",
        size = "250 ml",
        price = BigDecimal(6.0)
    )

    val LATTE = Product(
        id = 8L,
        name = "Latte",
        image = R.drawable.latte_small,
        description = "A latte is a coffee drink made with espresso and steamed milk.",
        size = "300 ml",
        price = BigDecimal(6.0)
    )

    private val LATTE_MACCHIATO = Product(
        id = 9L,
        name = "Latte Macchiato",
        image = R.drawable.latte_macchiato_small,
        description = "Latte Macchiato is a coffee beverage; the name literally means stained milk.",
        size = "300 ml",
        price = BigDecimal(6.5)
    )

    val MOCHA = Product(
        id = 10L,
        name = "Mocha",
        image = R.drawable.mocha_small,
        description = "A Mocha, also called mocaccino, is a chocolate-flavored variant of a Latte.",
        size = "300 ml",
        price = BigDecimal(6.0)
    )

    fun getBasketProducts() = listOf(
        BasketProduct(
            product = ESPRESSO,
            count = 1
        ),
        BasketProduct(
            product = LATTE_MACCHIATO,
            count = 2
        ),
        BasketProduct(
            product = IRISH_COFFEE,
            count = 2
        ),
        BasketProduct(
            product = CAPPUCCINO,
            count = 1
        )
    )

    fun getCoffeeDrinks() = listOf(
        AMERICANO,
        CAPPUCCINO,
        ESPRESSO,
        ESPRESSO_MACCHIATO,
        FRAPPINO,
        ICED_MOCHA,
        IRISH_COFFEE,
        LATTE,
        LATTE_MACCHIATO,
        MOCHA
    )
}