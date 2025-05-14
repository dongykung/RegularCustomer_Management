package com.dkproject.regularcustomermanagement.presentation.navigation

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.PersonPin
import androidx.compose.material.icons.filled.SwapHorizontalCircle
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavController
import androidx.navigation.NavOptions
import com.dkproject.regularcustomermanagement.R
import kotlinx.serialization.Serializable
import kotlin.reflect.KClass



@Serializable data class Customer(
    val initialCustomerId: String? = null,
)

@Serializable data object Home

@Serializable data object Handover

enum class TopLevelDestination(
    val icon: ImageVector,
    @StringRes val title: Int,
    @StringRes val description: Int,
    val route: KClass<*>
) {
    HOME(Icons.Filled.Home, R.string.home, R.string.home, Home::class),
    CUSTOMER(Icons.Filled.PersonPin, R.string.customer, R.string.customer, Customer::class),
    HANDOVER(Icons.Filled.SwapHorizontalCircle, R.string.handover, R.string.handover, Handover::class)
}

sealed class DetailScreen {
    @Serializable
    data object CreateCustomer: DetailScreen()
}

fun NavController.navigateToHome(navOptions: NavOptions) = navigate(route = Home, navOptions)

fun NavController.navigateToHandover(navOptions: NavOptions) = navigate(route = Handover, navOptions)

fun NavController.navigateToCustomers(
    initialCustomerId: String? = null,
    navOptions: NavOptions? = null,
) {
    navigate(route = Customer(initialCustomerId), navOptions)
}

