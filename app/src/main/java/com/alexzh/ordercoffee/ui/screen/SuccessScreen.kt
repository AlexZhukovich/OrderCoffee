package com.alexzh.ordercoffee.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.alexzh.ordercoffee.R
import com.alexzh.ordercoffee.ui.navigation.Screen

@Composable
fun SuccessScreen(
    navController: NavController
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(32.dp)
    ) {
        Text(
            text = "Payed successfully",
            fontSize = 32.sp,
            modifier = Modifier.align(Alignment.TopCenter)
        )

        Image(
            painter = painterResource(id = R.drawable.ic_coffee),
            contentDescription = null,
            modifier = Modifier.align(Alignment.Center)
                .size(96.dp),
            colorFilter = ColorFilter.tint(Color.Green)
        )

        Button(
            onClick = {
                navController.navigate(Screen.Home.route) {
                    popUpTo(Screen.Success.route) { inclusive = true }
                }
            },
            modifier = Modifier.align(Alignment.BottomCenter)
        ) {
            Text(
                text = "DONE",
                fontSize = 18.sp
            )
        }
    }
}

@Preview
@Composable
fun SuccessScreen_Preview() {
    SuccessScreen(navController = rememberNavController())
}