package com.sopt.dive

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.sopt.dive.login.LoginActivity
import com.sopt.dive.main.BottomNavigationBar
import com.sopt.dive.navigation.DiveNavHost
import com.sopt.dive.ui.theme.DiveTheme
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