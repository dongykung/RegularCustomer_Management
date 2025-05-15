package com.dkproject.regularcustomermanagement.presentation.navigation

import androidx.compose.material3.Text
import androidx.compose.material3.adaptive.WindowAdaptiveInfo
import androidx.compose.material3.adaptive.currentWindowAdaptiveInfo
import androidx.compose.runtime.Composable
import androidx.compose.runtime.internal.composableLambda
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.dkproject.regularcustomermanagement.presentation.DkAppState
import com.dkproject.regularcustomermanagement.presentation.ui.customer.Customer2PaneViewModel
import com.dkproject.regularcustomermanagement.presentation.ui.customer.CustomerListDetailScreen

@Composable
fun AppNavigation(
    appState: DkAppState,
    modifier: Modifier = Modifier,
    windowAdaptiveInfo: WindowAdaptiveInfo = currentWindowAdaptiveInfo(),
) {
    val navController = appState.navController
    NavHost(modifier = modifier, navController = navController, startDestination = Home) {
        composable<Home> {
            Text("This is home")
        }

        composable<Customer> {
            CustomerListDetailScreen()
        }

        composable<Handover> {
            Text("This is Handover")
        }
    }
}
