package com.sopt.dive.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.sopt.dive.navigation.DiveNavHost
import com.sopt.dive.ui.theme.DiveTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()
        setContent {
            DiveTheme {
                val navController = rememberNavController()
                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val currentDestination = navBackStackEntry?.destination

                // 사용자 정보를 저장할 상태
                var currentUserId by remember { mutableStateOf("") }
                var currentUserPw by remember { mutableStateOf("") }
                var currentUserNickname by remember { mutableStateOf("") }
                var currentUserDrink by remember { mutableStateOf("") }

                val showBottomBar = currentDestination?.let { destination ->
                    MainTab.contains { route ->
                        destination.hasRoute(route::class)
                    }
                } ?: false

                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    bottomBar = {
                        if (showBottomBar) {
                            BottomNavigationBar(
                                navController = navController,
                                currentDestination = currentDestination
                            )
                        }
                    }
                ) { innerPadding ->
                    DiveNavHost(
                        navController = navController,
                        paddingValues = innerPadding,
                        userId = currentUserId,
                        userPw = currentUserPw,
                        userNickname = currentUserNickname,
                        userDrink = currentUserDrink,
                        onUserInfoChanged = { userId, userPw, nickname, drink ->
                            currentUserId = userId
                            currentUserPw = userPw
                            currentUserNickname = nickname
                            currentUserDrink = drink
                        }
                    )
                }
            }
        }
    }
}