package com.dkproject.regularcustomermanagement

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.dkproject.regularcustomermanagement.presentation.Component.AdaptiveScaffold
import com.dkproject.regularcustomermanagement.presentation.navigation.AppNavigation
import com.dkproject.regularcustomermanagement.presentation.theme.RegularCustomerManagementTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            RegularCustomerManagementTheme {
                val navController: NavHostController = rememberNavController()
                val windowSize = calculateWindowSizeClass(this)

                AdaptiveScaffold(navController = navController, windowSize = windowSize.widthSizeClass) { innerPadding ->
                    AppNavigation(navController, modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}



