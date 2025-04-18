package com.dkproject.regularcustomermanagement.presentation.Component

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import com.dkproject.regularcustomermanagement.R
import com.dkproject.regularcustomermanagement.presentation.navigation.TabScreen
import com.dkproject.regularcustomermanagement.presentation.navigation.TabScreenList.tabNavItem

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CompactTopAppBar(
    currentRoute: String?
) {
    val title = when (currentRoute) {
        TabScreen.Home.route -> stringResource(R.string.home)
        TabScreen.Customer.route -> stringResource(R.string.customer)
        TabScreen.Handover.route -> stringResource(R.string.handover)
        else -> ""
    }
    AnimatedVisibility(tabNavItem.any { it.route == currentRoute }) {
        Column {
            TopAppBar(title = { Text(title, fontWeight = FontWeight.Bold) })
            AnimatedVisibility(currentRoute == TabScreen.Customer.route) {
                Text("검색바")
            }
        }
    }
}