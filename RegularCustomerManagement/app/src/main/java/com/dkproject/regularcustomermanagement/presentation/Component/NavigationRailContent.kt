package com.dkproject.regularcustomermanagement.presentation.Component

import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationRail
import androidx.compose.material3.NavigationRailItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.dkproject.regularcustomermanagement.presentation.navigation.TabScreen

@Composable
fun NavigationRailContent(
    navController: NavController,
) {
    val navItems = listOf(
        TabScreen.Home,
        TabScreen.Customer,
        TabScreen.Handover
    )
    val currentBackStackEntry by navController.currentBackStackEntryAsState()
    NavigationRail() {
        navItems.forEach { item ->
            NavigationRailItem(
                selected = item.route == currentBackStackEntry?.destination?.route,
                icon = { Icon(imageVector = item.icon, contentDescription = stringResource(item.description)) },
                onClick = {
                    navController.navigate(item.route) {
                        popUpTo(navController.graph.id) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                },
                label = { Text(text = stringResource(item.title), fontWeight = FontWeight.Bold) }
            )
        }
    }
}