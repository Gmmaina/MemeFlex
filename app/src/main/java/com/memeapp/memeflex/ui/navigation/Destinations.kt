package com.memeapp.memeflex.ui.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.ui.graphics.vector.ImageVector

// Screen destinations
sealed class Screen(val route: String) {
    object Splash : Screen("splash")
    object Login : Screen("login")
    object Register : Screen("register")
    object Feed : Screen("feed")
    object CreateMeme : Screen("create_meme")
    object Profile : Screen("profile")
    object Search : Screen("search")
    object MemeDetail : Screen("meme_detail/{memeId}") {
        fun createRoute(memeId: String) = "meme_detail/$memeId"
    }
    object UserProfile : Screen("user_profile/{userId}") {
        fun createRoute(userId: String) = "user_profile/$userId"
    }
}

// Bottom navigation items
data class BottomNavItem(
    val screen: Screen,
    val icon: ImageVector,
    val label: String
)

val bottomNavItems = listOf(
    BottomNavItem(
        screen = Screen.Feed,
        icon = Icons.Default.Home,
        label = "Feed"
    ),
    BottomNavItem(
        screen = Screen.Search,
        icon = Icons.Default.Search,
        label = "Search"
    ),
    BottomNavItem(
        screen = Screen.CreateMeme,
        icon = Icons.Default.Add,
        label = "Create"
    ),
    BottomNavItem(
        screen = Screen.Profile,
        icon = Icons.Default.Person,
        label = "Profile"
    )
)