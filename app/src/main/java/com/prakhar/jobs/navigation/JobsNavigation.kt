package com.prakhar.jobs.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.prakhar.jobs.screens.detail.DetailScreen
import com.prakhar.jobs.screens.home.HomeScreen
import kotlinx.serialization.Serializable

@Composable
fun JobsNavigation() {

    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Home) {

        composable<Home> {
            HomeScreen(navController)
        }

        composable<Detail> {
            val args = it.toRoute<Detail>()
           DetailScreen(Detail(args.jobId, args.jobTitle, args.jobWhatsappNumber, args.jobPlace, args.jobSalary, args.jobOtherDetails)
           )
        }

    }
}

@Serializable
object Home

@Serializable
data class Detail(val jobId:Int,
                  val jobTitle: String,
                  val jobWhatsappNumber: String,
                  val jobPlace: String,
                  val jobSalary: String,
                  val jobOtherDetails: String)
