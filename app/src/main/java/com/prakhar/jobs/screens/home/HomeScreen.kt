package com.prakhar.jobs.screens.home

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.prakhar.jobs.components.JobsTabView
import com.prakhar.jobs.screens.home.views.JobBookmark
import com.prakhar.jobs.screens.home.views.JobsView

@Composable
fun HomeScreen(
    viewModel: HomeScreenViewModel = hiltViewModel()
) {

    val homeNavController = rememberNavController()

    val jobsTab = TabBarItem(
        title = "Jobs", selectedIcon = Icons.Filled.Person, unselectedIcon = Icons.Outlined.Person
    )

    val bookmarkTab = TabBarItem(
        title = "Bookmarks",
        selectedIcon = Icons.Filled.Favorite,
        unselectedIcon = Icons.Outlined.FavoriteBorder,
    )

    val tabBarItems = listOf(jobsTab, bookmarkTab)


    Scaffold(bottomBar = { JobsTabView(tabBarItems, homeNavController) }) { contentPadding ->

        Surface(
            modifier = Modifier
                .fillMaxSize()
                .padding(contentPadding)
        ) {

            NavHost(navController = homeNavController, startDestination = jobsTab.title) {

                composable(jobsTab.title) {

                    JobsView(viewModel = viewModel)
                }

                composable(bookmarkTab.title) {

                    JobBookmark()
                }
            }
        }
    }

}

data class TabBarItem(
    val title: String,
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector,
    val badgeAmount: Int? = null
)