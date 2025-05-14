package com.dkproject.regularcustomermanagement

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import com.dkproject.regularcustomermanagement.presentation.CustomerManageApp
import com.dkproject.regularcustomermanagement.presentation.rememberDkAppState
import com.dkproject.regularcustomermanagement.presentation.theme.RegularCustomerManagementTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val appState = rememberDkAppState()
            RegularCustomerManagementTheme {
                CustomerManageApp(appState)
            }
        }
    }
}



