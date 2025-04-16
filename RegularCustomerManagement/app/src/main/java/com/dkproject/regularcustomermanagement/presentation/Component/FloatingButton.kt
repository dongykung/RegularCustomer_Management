package com.dkproject.regularcustomermanagement.presentation.Component

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.FloatingActionButtonElevation
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import com.dkproject.regularcustomermanagement.R
import com.dkproject.regularcustomermanagement.presentation.navigation.DetailScreen
import com.dkproject.regularcustomermanagement.presentation.navigation.TabScreen
import com.dkproject.regularcustomermanagement.presentation.navigation.TabScreenList
import com.dkproject.regularcustomermanagement.presentation.navigation.TabScreenList.tabFloatingItem

@Composable
fun FloatingButton(
    navController: NavHostController,
    shape: Shape = FloatingActionButtonDefaults.shape,
    elevation: FloatingActionButtonElevation = FloatingActionButtonDefaults.elevation(),
    currentRoute: String? = null,
) {
    AnimatedVisibility(tabFloatingItem.any { it.route == currentRoute }) {
        FloatingActionButton(
            onClick = {
                when (currentRoute) {
                    TabScreen.Home.route -> {}
                    TabScreen.Customer.route -> { navController.navigate(DetailScreen.CreateCustomer)}
                }
            },
            shape = shape,
            elevation = elevation
        ) {
            Icon(Icons.Filled.Add, "description")
        }
    }
}