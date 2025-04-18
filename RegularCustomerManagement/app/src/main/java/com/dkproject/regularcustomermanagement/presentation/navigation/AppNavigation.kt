package com.dkproject.regularcustomermanagement.presentation.navigation

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.dkproject.regularcustomermanagement.presentation.ui.createCustomer.CreateCustomerScreen
import com.dkproject.regularcustomermanagement.presentation.ui.createCustomer.CreateCustomerViewModel
import com.dkproject.regularcustomermanagement.presentation.ui.customer.CustomerScreen

@Composable
fun AppNavigation(
    navController: NavHostController = rememberNavController(),
    modifier: Modifier = Modifier,
) {
    NavHost(navController = navController, startDestination = DetailScreen.CreateCustomer) {
        composable(TabScreen.Home.route) {
            Column(modifier = modifier) {
                Text("this is home")
            }
        }

        composable(TabScreen.Customer.route) {
            CustomerScreen(modifier = modifier)
        }

        composable<DetailScreen.CreateCustomer> {
            val viewModel: CreateCustomerViewModel = hiltViewModel()
            val uiState by viewModel.uiState.collectAsStateWithLifecycle()
            CreateCustomerScreen(
                uiState = uiState, onMoveStep = viewModel::updateCurrentStep,
                popBackStack = { navController.popBackStack() },
                updateBasicInfo = viewModel::updateBasicInfo,
                updateAffiliationAndTags = viewModel::updateAffiliationAndTags,
                updateStarAndMemo = viewModel::updateStarAndMemo,
                onCompleted = viewModel::addCustomer
            )
        }

        composable(TabScreen.Handover.route) {
            Text(text = "this is handover")
        }
    }
}
