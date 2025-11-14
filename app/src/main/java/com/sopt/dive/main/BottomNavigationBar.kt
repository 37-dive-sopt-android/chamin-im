package com.sopt.dive.main

import androidx.compose.foundation.layout.RowScope
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.toRoute
import com.sopt.dive.navigation.Home
import com.sopt.dive.navigation.MyPage
import com.sopt.dive.ui.theme.Teel200
import com.sopt.dive.ui.theme.Teel700

@Composable
fun BottomNavigationBar(
    navController: NavHostController,
    currentDestination: NavDestination?
) {
    val navBackStackEntry = navController.currentBackStackEntryAsState().value
    val homeRoute = navBackStackEntry?.destination?.route?.let {
        try {
            navController.currentBackStackEntry?.toRoute<Home>()
        } catch (e: Exception) {
            null
        }
    }
    // id
    val currentUserId = homeRoute?.userId ?: ""

    NavigationBar(
        containerColor = Teel200,
        contentColor = Teel700
    ) {
        MainTab.entries.forEach { tab ->
            val isSelected = currentDestination?.hasRoute(tab.route::class) == true

            DiveNavigationBarItem(
                isSelected = isSelected,
                label = tab.label,
                icon = tab.icon,
                onClick = {
                    if (!isSelected) {
                        val route = when (tab) {
                            MainTab.HOME -> Home(userId = currentUserId)
                            MainTab.MY -> MyPage(userId = currentUserId)
                            else -> tab.route
                        }

                        navController.navigate(route) {
                            launchSingleTop = true
                            popUpTo(navController.graph.startDestinationId) {
                                saveState = true
                            }
                            restoreState = true
                        }
                    }
                }
            )
        }
    }
}

@Composable
private fun RowScope.DiveNavigationBarItem(
    isSelected: Boolean,
    onClick: () -> Unit,
    label: String,
    icon: ImageVector
) {
    NavigationBarItem(
        selected = isSelected,
        onClick = onClick,
        icon = {
            Icon(
                imageVector = icon,
                contentDescription = label
            )
        },
        label = { Text(label) },
        colors = NavigationBarItemDefaults.colors(
            selectedIconColor = Teel700,
            selectedTextColor = Teel700,
            unselectedIconColor = Color.Gray,
            unselectedTextColor = Color.Gray,
            indicatorColor = Color.White
        )
    )
}