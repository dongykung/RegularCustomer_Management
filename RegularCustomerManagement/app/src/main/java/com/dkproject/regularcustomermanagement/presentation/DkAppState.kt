package com.dkproject.regularcustomermanagement.presentation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.util.trace
import androidx.navigation.NavDestination
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navOptions
import com.dkproject.regularcustomermanagement.presentation.navigation.TopLevelDestination
import com.dkproject.regularcustomermanagement.presentation.navigation.navigateToCustomers
import com.dkproject.regularcustomermanagement.presentation.navigation.navigateToHandover
import com.dkproject.regularcustomermanagement.presentation.navigation.navigateToHome

@Composable
fun rememberDkAppState(
    navController: NavHostController = rememberNavController(),
): DkAppState {
    return remember(
        navController,
    ) {
        DkAppState(
            navController = navController,
        )
    }
}


@Stable
class DkAppState(
    val navController: NavHostController,
) {
    private val previousDestination = mutableStateOf<NavDestination?>(null)

    val currentDestination: NavDestination?
        @Composable get() {
            val currentEntry = navController.currentBackStackEntryFlow
                .collectAsState(initial = null)

            return currentEntry.value?.destination.also { destination ->
                if (destination != null) {
                    previousDestination.value = destination
                }
            } ?: previousDestination.value
        }

    val topLevelDestinations: List<TopLevelDestination> = TopLevelDestination.entries

    fun navigateToTopLevelDestination(topLevelDestination: TopLevelDestination) {
        trace("Navigation: ${topLevelDestination.name}") {
            val topLevelNavOptions = navOptions {
                // Pop up to the start destination of the graph to
                // avoid building up a large stack of destinations
                // on the back stack as users select items
                popUpTo(navController.graph.findStartDestination().id) {
                    saveState = true
                }
                // Avoid multiple copies of the same destination when
                // reselecting the same item
                launchSingleTop = true
                // Restore state when reselecting a previously selected item
                restoreState = true
            }

            when (topLevelDestination) {
                TopLevelDestination.HOME -> navController.navigateToHome(topLevelNavOptions)
                TopLevelDestination.CUSTOMER -> { navController.navigateToCustomers(null, topLevelNavOptions)}
                TopLevelDestination.HANDOVER -> { navController.navigateToHandover(topLevelNavOptions)}
            }
        }
    }
}
