package com.dkproject.regularcustomermanagement.presentation.Component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.PermanentDrawerSheet
import androidx.compose.material3.PermanentNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.dkproject.regularcustomermanagement.R

@OptIn(ExperimentalMaterial3Api::class)
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
            val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()
            Scaffold(
                modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
                topBar = {
                    CompactTopAppBar(
                        currentRoute = currentRoute,
                        scrollBehavior = scrollBehavior
                    )
                },
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
                content(innerPadding)
            }
        }

        WindowWidthSizeClass.Medium -> {
            Row(
                modifier = Modifier
                    .fillMaxSize()
            ) {
                NavigationRailContent(navController = navController)
                Box(modifier = Modifier.statusBarsPadding()) {
                    content(PaddingValues())
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
                Box(modifier = Modifier.fillMaxSize().systemBarsPadding()) {
                    content(PaddingValues())
                }
            }
        }
    }
}