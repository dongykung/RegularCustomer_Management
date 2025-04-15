package com.dkproject.regularcustomermanagement.presentation.Component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.dkproject.regularcustomermanagement.presentation.navigation.TabScreen

@Composable
fun NavigationDrawerContent(
    modifier: Modifier = Modifier,
    navController: NavHostController
) {
    val navItems = listOf(
        TabScreen.Home,
        TabScreen.Customer,
        TabScreen.Handover
    )
    val currentBackStackEntry by navController.currentBackStackEntryAsState()
    Column(modifier = modifier) {
        Spacer(Modifier.height(12.dp))
        navItems.forEach { item ->
            NavigationDrawerItem(
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
                label = { Text(text = stringResource(item.title), fontWeight = FontWeight.Bold) },
                modifier = Modifier.padding(horizontal = 12.dp, vertical = 4.dp)
            )
        }
    }
}
