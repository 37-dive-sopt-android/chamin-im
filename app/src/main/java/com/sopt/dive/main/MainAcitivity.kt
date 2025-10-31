package com.sopt.dive.main

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
import androidx.compose.ui.graphics.vector.ImageVector
import com.sopt.dive.home.HomeScreen
import com.sopt.dive.login.LoginActivity
import com.sopt.dive.my.MyScreen
import com.sopt.dive.search.SearchScreen
import com.sopt.dive.ui.theme.DiveTheme
import com.sopt.dive.ui.theme.Teel200
import com.sopt.dive.ui.theme.Teel700
import com.sopt.dive.util.KeyStorage

data class BottomNavItem(
    val title: String,
    val icon: ImageVector
)

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
                MainScreen(
                    userId = userId,
                    userPw = userPw,
                    userNickname = userNickname,
                    userDrink = userDrink
                )
            }
        }
    }
}

@Composable
fun MainScreen(
    userId: String,
    userPw: String,
    userNickname: String,
    userDrink: String
) {
    var selectedIndex by remember { mutableIntStateOf(0) }

    val navItems = listOf(
        BottomNavItem("Home", Icons.Default.Home),
        BottomNavItem("Search", Icons.Default.Search),
        BottomNavItem("My", Icons.Default.Person)
    )

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        bottomBar = {
            NavigationBar(
                containerColor = Teel200,
                contentColor = Teel700
            ) {
                navItems.forEachIndexed { index, item ->
                    NavigationBarItem(
                        selected = selectedIndex == index,
                        onClick = { selectedIndex = index },
                        icon = {
                            Icon(
                                imageVector = item.icon,
                                contentDescription = item.title
                            )
                        },
                        label = { Text(item.title) },
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
        }
    ) { innerPadding ->
        when (selectedIndex) {
            0 -> HomeScreen(
                modifier = Modifier
                    .padding(innerPadding)
                    .fillMaxSize()
            )
            1 -> SearchScreen(
                modifier = Modifier
                    .padding(innerPadding)
                    .fillMaxSize()
            )
            2 -> MyScreen(
                modifier = Modifier
                    .padding(innerPadding)
                    .fillMaxSize(),
                userId = userId,
                userPw = userPw,
                userNickname = userNickname,
                userDrink = userDrink
            )
        }
    }
}