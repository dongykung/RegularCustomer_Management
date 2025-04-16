package com.dkproject.regularcustomermanagement.presentation.Component

import android.util.Log
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.toRoute
import com.dkproject.regularcustomermanagement.presentation.navigation.TabScreen
import com.dkproject.regularcustomermanagement.presentation.navigation.TabScreenList.tabNavItem

@Composable
fun BottomNavigationBar(
    navController: NavHostController,
    currentRoute : String? = null
) {
    val navItem = tabNavItem
    AnimatedVisibility(navItem.any { it.route == currentRoute }) {
        NavigationBar(tonalElevation = 0.dp) {
            navItem.forEach { item ->
                NavigationBarItem(
                    selected = item.route == currentRoute,
                    onClick = {
                        navController.navigate(item.route) {
                            popUpTo(navController.graph.id) {
                                saveState = true
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                    },
                    icon = {
                        Icon(
                            imageVector = item.icon,
                            contentDescription = stringResource(item.description)
                        )
                    },
                    label = {
                        Text(
                            text = stringResource(item.title),
                            fontWeight = FontWeight.Bold
                        )
                    },
                    colors = NavigationBarItemDefaults.colors(
                        indicatorColor = Color.Transparent,
                        selectedIconColor = MaterialTheme.colorScheme.primary,
                        selectedTextColor = MaterialTheme.colorScheme.primary
                    )
                )
            }
        }
    }
}