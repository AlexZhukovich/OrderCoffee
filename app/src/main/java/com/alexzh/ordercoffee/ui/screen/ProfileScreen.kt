package com.alexzh.ordercoffee.ui.screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.alexzh.ordercoffee.R
import com.alexzh.ordercoffee.model.ProfileItem

@Composable
fun ProfileScreen() {
    val items = listOf(
        ProfileItem(
            id = 1L,
            title = "Personal information",
            icon = R.drawable.ic_profile
        ),
        ProfileItem(
            id = 2L,
            title = "Order history",
            icon = R.drawable.ic_basket
        )
    )

    Column {
        TopAppBar {
            Text(
                text = "Profile",
                modifier = Modifier.padding(horizontal = 12.dp),
                fontSize = 18.sp
            )
        }
        ProfileItemList(
            items = items
        )
    }
}

@Composable
fun ProfileItemList(
    items: List<ProfileItem>
) {
    LazyColumn {
        items.forEach { item ->
            item {
                ProfileListItem(item)
            }
        }
    }
}

@Composable
fun ProfileListItem(
    item: ProfileItem
) {
    Row(
        horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.fillMaxWidth()
            .height(50.dp)
            .clickable {

            }
            .padding(horizontal = 16.dp, vertical = 8.dp)
    ) {
        // TODO: fix contentDescription
        Icon(
            painter = painterResource(id = item.icon),
            contentDescription = null,
            modifier = Modifier.fillMaxHeight()
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(
            text = item.title,
            fontWeight = FontWeight.Bold,
            fontSize = 18.sp
        )
    }
}

@Preview
@Composable
fun ProfileItem_Preview() {
    val item = ProfileItem(
        id = 1L,
        title = "Test menu item",
        icon = R.drawable.ic_profile
    )
    ProfileListItem(item)
}

@Preview
@Composable
fun ProfileScreen_Preview() {
    ProfileScreen()
}