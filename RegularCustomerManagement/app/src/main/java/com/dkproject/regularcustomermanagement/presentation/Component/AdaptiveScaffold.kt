package com.dkproject.regularcustomermanagement.presentation.Component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.PermanentDrawerSheet
import androidx.compose.material3.PermanentNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController

@Composable
fun AdaptiveScaffold(
    navController: NavHostController,
    windowSize: WindowWidthSizeClass,
    content: @Composable (PaddingValues) -> Unit
) {
    when (windowSize) {
        WindowWidthSizeClass.Compact -> {
            Scaffold(
                bottomBar = { BottomNavigationBar(navController = navController) }
            ) { innerPadding ->
                Box(modifier = Modifier.padding(innerPadding))
                content(innerPadding)
            }
        }
        WindowWidthSizeClass.Medium -> {
            Scaffold { innerPadding ->
                Row(modifier = Modifier.padding(innerPadding).fillMaxSize()) {
                    NavigationRailContent(navController = navController)
                    Box(modifier = Modifier.fillMaxSize().padding(start = 16.dp)) {
                        content(PaddingValues())
                    }
                }
            }
        }
        WindowWidthSizeClass.Expanded -> {
            PermanentNavigationDrawer(
                drawerContent = {
                    PermanentDrawerSheet(
                        modifier = Modifier.width(240.dp),
                        drawerContainerColor = MaterialTheme.colorScheme.inverseOnSurface
                    ) { // PermanentDrawerSheet Scope
                        NavigationDrawerContent(navController = navController)
                    }
                }
            ) {
                Box(modifier = Modifier.fillMaxSize()) {
                    content(PaddingValues())
                }
            }
        }
    }
}