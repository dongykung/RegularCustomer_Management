package com.dkproject.regularcustomermanagement.presentation.navigation

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.PersonPin
import androidx.compose.material.icons.filled.SwapHorizontalCircle
import androidx.compose.ui.graphics.vector.ImageVector
import com.dkproject.regularcustomermanagement.R
import kotlinx.serialization.Serializable

sealed class TabScreen(
    val route: String, val icon: ImageVector, @StringRes val title: Int, @StringRes val description: Int
) {
    data object Home: TabScreen("home", Icons.Filled.Home, R.string.home, R.string.home)
    data object Customer: TabScreen("customer", Icons.Filled.PersonPin, R.string.customer, R.string.customer)
    data object Handover: TabScreen("handover", Icons.Filled.SwapHorizontalCircle, R.string.handover, R.string.handover)
}

sealed class DetailScreen {
    @Serializable
    data object CreateCustomer: DetailScreen()
}

object TabScreenList {
    val tabNavItem = listOf(
        TabScreen.Home,
        TabScreen.Customer,
        TabScreen.Handover
    )

    val tabFloatingItem = listOf(
        TabScreen.Home,
        TabScreen.Customer,
    )
}