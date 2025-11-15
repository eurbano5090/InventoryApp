package com.example.inventory.core.navigation

import android.R.attr.type
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.inventory.view.auth.home.HomeScreen
import com.example.inventory.view.auth.home.HomeViewModel
import com.example.inventory.view.auth.home.component.AddScreen
import com.example.inventory.view.auth.home.component.EditScreen


@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    val homeViewModel: HomeViewModel = hiltViewModel()

    NavHost(navController = navController,
        startDestination = "home") {
        composable("home") {
            HomeScreen(
                navController = navController,
                homeViewModel = homeViewModel
            )
        }

        composable(
            route = "edit/{id}",
            arguments = listOf(navArgument("id") { type = NavType.IntType })
        ) { backStackEntry ->
            val id = backStackEntry.arguments?.getInt("id")
            id?.let {
                EditScreen(
                    id = it,
                    navController = navController,
                    homeViewModel=homeViewModel
                )
            }
        }

        composable(
            route = "add/{id}",
            arguments = listOf(navArgument("id") { type = NavType.IntType })
        ) { backStackEntry ->
            val id = backStackEntry.arguments?.getInt("id")
            id?.let {
                AddScreen(
                    id = it,
                    navController = navController,
                    homeViewModel=homeViewModel
                )
            }
        }
    }
}