package com.alexzh.ordercoffee.ui.screen.success

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.alexzh.ordercoffee.R
import com.alexzh.ordercoffee.ui.common.UiState
import com.alexzh.ordercoffee.ui.navigation.Screen

@Composable
fun SuccessScreen(
    navController: NavController,
    viewModel: SuccessViewModel = SuccessViewModel()
) {
    viewModel.loadCoffeeDrinks()
    viewModel.uiState.observeAsState(initial = UiState.Loading).value.let { uiState ->
        when (uiState) {
            is UiState.Loading -> { }
            is UiState.Success -> {
                Column(
                    modifier = Modifier.fillMaxSize()
                        .background(MaterialTheme.colors.background)
                        .padding(16.dp)
                ) {
                    Text(
                        text = "Payed successfully",
                        fontSize = 32.sp,
                        modifier = Modifier.align(Alignment.CenterHorizontally)
                    )
                    Box(
                        modifier = Modifier
                            .weight(1.0f)
                            .padding(vertical = 32.dp)
                            .fillMaxWidth()
                    ) {
                        Card {
                            Column {
                                Image(
                                    painter = painterResource(id = R.drawable.espresso_small),
                                    contentDescription = null,
                                    modifier = Modifier
                                        .size(96.dp)
                                        .padding(start = 8.dp)
                                        .align(alignment = Alignment.CenterHorizontally)
                                )
                                LazyColumn(
                                    modifier = Modifier.padding(vertical = 8.dp)
                                ) {
                                    uiState.data.forEach {
                                        item {
                                            Row(
                                                modifier = Modifier
                                                    .fillMaxWidth()
                                                    .padding(horizontal = 16.dp)
                                            ) {
                                                Text(
                                                    text = it.product.name,
                                                    fontSize = 24.sp,
                                                    modifier = Modifier.weight(1f)
                                                )
                                                Text(
                                                    text = it.count.toString(),
                                                    fontSize = 24.sp,
                                                    fontWeight = FontWeight.Bold
                                                )
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }

                    Button(
                        onClick = {
                            viewModel.clear()
                            navController.navigate(Screen.Home.route) {
                                this.restoreState
                                popUpTo(Screen.Success.route) { inclusive = true }
                            }
                        },
                        modifier = Modifier.align(Alignment.CenterHorizontally)
                    ) {
                        Text(
                            text = "DONE",
                            fontSize = 18.sp
                        )
                    }
                }
            }
            is UiState.Error -> { }
        }
    }
}

@Preview
@Composable
fun SuccessScreen_Preview() {
    SuccessScreen(
        navController = rememberNavController()
    )
}