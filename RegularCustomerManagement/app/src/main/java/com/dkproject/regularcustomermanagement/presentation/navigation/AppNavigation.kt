package com.dkproject.regularcustomermanagement.presentation.navigation

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@Composable
fun AppNavigation(
    navController: NavHostController = rememberNavController(),
    modifier: Modifier = Modifier,
) {
    NavHost(navController = navController, startDestination = TabScreen.Home.route) {
        composable(TabScreen.Home.route) {
            Column(modifier = modifier) {
                Text("this is home")
            }
        }

        composable(TabScreen.Customer.route) {
            Text("this is customer")
        }

        composable(TabScreen.Handover.route) {
            Text(text = "this is handover")
        }
    }
}
