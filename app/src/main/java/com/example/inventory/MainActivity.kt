package com.example.inventory

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.isSystemInDarkTheme
import com.example.inventory.core.navigation.AppNavigation
import com.example.inventory.ui.theme.InventoryApp
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val userPrefersDark = isSystemInDarkTheme()
            InventoryApp(darkTheme = userPrefersDark){
                AppNavigation()
            }
        }
    }
}
