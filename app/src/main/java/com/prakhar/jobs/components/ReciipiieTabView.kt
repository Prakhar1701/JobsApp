package com.prakhar.jobs.components

import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.prakhar.jobs.screens.home.TabBarItem

data class TabBarItem(
    val title: String,
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector,
    val badgeAmount: Int? = null
)

@Composable
fun JobsTabView(tabBarItems: List<TabBarItem>, navController: NavController) {
    var selectedTabIndex by rememberSaveable {
        mutableIntStateOf(0)
    }

    NavigationBar {
        // looping over each tab to generate the views and navigation for each item
        tabBarItems.forEachIndexed { index, tabBarItem ->
            NavigationBarItem(selected = selectedTabIndex == index, onClick = {
                selectedTabIndex = index
                navController.navigate(tabBarItem.title)
            }, icon = {
                TabBarIconView(
                    isSelected = selectedTabIndex == index,
                    selectedIcon = tabBarItem.selectedIcon,
                    unselectedIcon = tabBarItem.unselectedIcon,
                    title = tabBarItem.title,
                    badgeAmount = tabBarItem.badgeAmount
                )
            }, label = { Text(tabBarItem.title, fontSize = 15.sp) })
        }
    }
}

@Composable
fun TabBarIconView(
    isSelected: Boolean,
    selectedIcon: ImageVector,
    unselectedIcon: ImageVector,
    title: String,
    badgeAmount: Int? = null
) {
    BadgedBox(badge = { TabBarBadgeView(badgeAmount) }) {
        Icon(
            imageVector = if (isSelected) {
                selectedIcon
            } else {
                unselectedIcon
            }, contentDescription = title
        )
    }
}

@Composable
fun TabBarBadgeView(count: Int? = null) {
    if (count != null) {
        Badge {
            Text(count.toString())
        }
    }
}
