package com.dkproject.regularcustomermanagement.presentation.Component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.PermanentDrawerSheet
import androidx.compose.material3.PermanentNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.dkproject.regularcustomermanagement.R

@Composable
fun AdaptiveScaffold(
    navController: NavHostController,
    windowSize: WindowWidthSizeClass,
    content: @Composable (PaddingValues) -> Unit
) {
    val currentBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = currentBackStackEntry?.destination?.route

    when (windowSize) {
        WindowWidthSizeClass.Compact -> {
            Scaffold(
                topBar = { CompactTopAppBar(currentRoute = currentRoute)},
                bottomBar = {
                    BottomNavigationBar(
                        navController = navController,
                        currentRoute = currentRoute
                    )
                },
                floatingActionButton = {
                    FloatingButton(
                        navController = navController,
                        currentRoute = currentRoute,
                        shape = CircleShape
                    )
                }
            ) { innerPadding ->
                Box(modifier = Modifier.padding(innerPadding))
                content(innerPadding)
            }
        }

        WindowWidthSizeClass.Medium -> {
            Scaffold { innerPadding ->
                Row(
                    modifier = Modifier
                        .padding(innerPadding)
                        .fillMaxSize()
                ) {
                    NavigationRailContent(navController = navController)
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(start = 16.dp)
                    ) {
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