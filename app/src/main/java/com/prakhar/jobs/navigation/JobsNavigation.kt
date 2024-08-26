package com.prakhar.jobs.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.prakhar.jobs.screens.home.HomeScreen
import kotlinx.serialization.Serializable

@Composable
fun JobsNavigation() {

    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = HomeScreen) {

        composable<HomeScreen> {
            HomeScreen()
        }
    }
}

@Serializable
object HomeScreen