// BottomNavigationBar.kt

package com.sopt.dive.main

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.NavHostController
import com.sopt.dive.navigation.Home
import com.sopt.dive.navigation.MyPage
import com.sopt.dive.navigation.Search
import com.sopt.dive.ui.theme.Teel200
import com.sopt.dive.ui.theme.Teel700

@Composable
fun BottomNavigationBar(
    navController: NavHostController,
    currentDestination: NavDestination?,
    userId: String,
    userPw: String,
    userNickname: String,
    userDrink: String
) {
    NavigationBar(
        containerColor = Teel200,
        contentColor = Teel700
    ) {
        val isHomeSelected = currentDestination?.hasRoute(Home::class) == true
        val isSearchSelected = currentDestination?.hasRoute(Search::class) == true
        val isMyPageSelected = currentDestination?.hasRoute(MyPage::class) == true

        // Home 탭
        NavigationBarItem(
            selected = isHomeSelected,
            onClick = {
                if (!isHomeSelected) {
                    navController.navigate(
                        Home(
                            userId = userId,
                            nickname = userNickname
                        )
                    ) {
                        launchSingleTop = true
                        popUpTo(navController.graph.startDestinationId) {
                            saveState = true
                        }
                        restoreState = true
                    }
                }
            },
            icon = {
                Icon(
                    imageVector = Icons.Default.Home,
                    contentDescription = "Home"
                )
            },
            label = { Text("Home") },
            colors = NavigationBarItemDefaults.colors(
                selectedIconColor = Teel700,
                selectedTextColor = Teel700,
                unselectedIconColor = Color.Gray,
                unselectedTextColor = Color.Gray,
                indicatorColor = Color.White
            )
        )

        // Search 탭
        NavigationBarItem(
            selected = isSearchSelected,
            onClick = {
                if (!isSearchSelected) {
                    navController.navigate(Search) {
                        launchSingleTop = true
                        popUpTo(navController.graph.startDestinationId) {
                            saveState = true
                        }
                        restoreState = true
                    }
                }
            },
            icon = {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = "Search"
                )
            },
            label = { Text("Search") },
            colors = NavigationBarItemDefaults.colors(
                selectedIconColor = Teel700,
                selectedTextColor = Teel700,
                unselectedIconColor = Color.Gray,
                unselectedTextColor = Color.Gray,
                indicatorColor = Color.White
            )
        )

        // My 탭
        NavigationBarItem(
            selected = isMyPageSelected,
            onClick = {
                if (!isMyPageSelected) {
                    navController.navigate(
                        MyPage(
                            userId = userId,
                            nickname = userNickname,
                            drink = userDrink,
                            userPw = userPw
                        )
                    ) {
                        launchSingleTop = true
                        popUpTo(navController.graph.startDestinationId) {
                            saveState = true
                        }
                        restoreState = true
                    }
                }
            },
            icon = {
                Icon(
                    imageVector = Icons.Default.Person,
                    contentDescription = "My"
                )
            },
            label = { Text("My") },
            colors = NavigationBarItemDefaults.colors(
                selectedIconColor = Teel700,
                selectedTextColor = Teel700,
                unselectedIconColor = Color.Gray,
                unselectedTextColor = Color.Gray,
                indicatorColor = Color.White
            )
        )
    }
}