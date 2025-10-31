package com.sopt.dive

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.sopt.dive.login.LoginActivity
import com.sopt.dive.main.BottomNavigationBar
import com.sopt.dive.navigation.DiveNavHost
import com.sopt.dive.navigation.Home
import com.sopt.dive.navigation.MyPage
import com.sopt.dive.navigation.Search
import com.sopt.dive.ui.theme.DiveTheme
import com.sopt.dive.ui.theme.Teel200
import com.sopt.dive.ui.theme.Teel700
import com.sopt.dive.util.KeyStorage


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val userId = intent.getStringExtra(KeyStorage.ID) ?: ""
        val userPw = intent.getStringExtra(KeyStorage.PW) ?: ""
        val userNickname = intent.getStringExtra(KeyStorage.NICKNAME) ?: ""
        val userDrink = intent.getStringExtra(KeyStorage.DRINK) ?: ""

        //로그인 정보 없을 때
        if (userId.isEmpty()) {
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
            return
        }

        enableEdgeToEdge()
        setContent {
            DiveTheme {
                val navController = rememberNavController()
                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val currentRoute = navBackStackEntry?.destination?.route

                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    bottomBar = {
                        BottomNavigationBar(
                            navController = navController,
                            currentRoute = currentRoute,
                            userId = userId,
                            userPw = userPw,
                            userNickname = userNickname,
                            userDrink = userDrink
                        )
                    }
                ) { innerPadding ->
                    DiveNavHost(
                        navController = navController,
                        paddingValues = innerPadding,
                        userId = userId,
                        userPw = userPw,
                        userNickname = userNickname,
                        userDrink = userDrink
                    )
                }
            }
        }
    }
}