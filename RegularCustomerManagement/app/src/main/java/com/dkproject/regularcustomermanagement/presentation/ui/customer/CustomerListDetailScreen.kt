package com.dkproject.regularcustomermanagement.presentation.ui.customer

import androidx.compose.material3.adaptive.ExperimentalMaterial3AdaptiveApi
import androidx.compose.material3.adaptive.WindowAdaptiveInfo
import androidx.compose.material3.adaptive.currentWindowAdaptiveInfo
import androidx.compose.material3.adaptive.layout.AnimatedPane
import androidx.compose.material3.adaptive.layout.calculatePaneScaffoldDirective
import androidx.compose.material3.adaptive.navigation.NavigableListDetailPaneScaffold
import androidx.compose.material3.adaptive.navigation.rememberListDetailPaneScaffoldNavigator
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import com.dkproject.regularcustomermanagement.presentation.ui.customer.customerList.CustomerListRoute


@OptIn(ExperimentalMaterial3AdaptiveApi::class)
@Composable
fun CustomerListDetailScreen(
    viewModel: Customer2PaneViewModel = hiltViewModel(),
    windowAdaptiveInfo: WindowAdaptiveInfo = currentWindowAdaptiveInfo(),
) {

    val listDetailNavigator = rememberListDetailPaneScaffoldNavigator(
        scaffoldDirective = calculatePaneScaffoldDirective(windowAdaptiveInfo),
    )
    NavigableListDetailPaneScaffold(
        navigator = listDetailNavigator,
        listPane = {
            AnimatedPane {
                CustomerListRoute()
            }
        },
        detailPane = {
            AnimatedPane {

            }
        },
    )
}



