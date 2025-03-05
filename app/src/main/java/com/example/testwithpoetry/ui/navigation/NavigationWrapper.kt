package com.example.testwithpoetry.ui.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.style.TextAlign
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.testwithpoetry.data.local.preferences.UserPreferences
import com.example.testwithpoetry.ui.screens.AuthorDetailScreen
import com.example.testwithpoetry.ui.screens.AuthorsScreen
import com.example.testwithpoetry.ui.screens.ProfileScreen
import com.example.testwithpoetry.ui.screens.WelcomeScreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NavigationWrapper(userPreferences: UserPreferences) {
    val navController = rememberNavController()
    var title by remember { mutableStateOf("") }

    val startDestination = if (userPreferences.hasUser()) {
        Navigate.Poetry.route
    } else {
        Navigate.Welcome.route
    }

    Scaffold(
        bottomBar = {
            val currentDestination =
                navController.currentBackStackEntryAsState().value?.destination?.route
            if (currentDestination != Navigate.Welcome.route) {
                BottomNavigationBar(navController)
            }
        },
        topBar = {
            val currentDestination =
                navController.currentBackStackEntryAsState().value?.destination?.route
            if (currentDestination != Navigate.Welcome.route) {
                TopAppBar(
                    title = { Text(title, textAlign = TextAlign.Center) }
                )
            }
        }
    ) { paddingValues ->
        NavHost(
            navController = navController,
            startDestination = startDestination,
            modifier = Modifier.padding(paddingValues)
        ) {
            composable(Navigate.Welcome.route) {
                title = ""
                WelcomeScreen {
                    navController.navigate(Navigate.Poetry.route)
                }
            }

            composable(Navigate.Poetry.route) {
                title = "Welcome ${userPreferences.getUserName()}"
                AuthorsScreen { authorName ->
                    navController.navigate("detail/$authorName")
                }
            }

            composable(Navigate.Profile.route) {
                title = "Profile"
                ProfileScreen()
            }

            composable(
                route = Navigate.AuthorDetail.route,
                arguments = listOf(navArgument("authorName") { defaultValue = "" })
            ) { backStackEntry ->
                val authorName = backStackEntry.arguments?.getString("authorName") ?: ""
                title = authorName
                AuthorDetailScreen(authorName)
            }
        }
    }
}

@Composable
fun BottomNavigationBar(navController: NavController) {
    val items = listOf(
        Navigate.Poetry,
        Navigate.Profile
    )

    NavigationBar {
        val currentRoute =
            navController.currentBackStackEntryAsState().value?.destination?.route.orEmpty()
        items.forEach { screen ->
            NavigationBarItem(
                icon = {
                    Icon(
                        imageVector = screen.icon,
                        contentDescription = screen.label
                    )
                },
                label = { Text(screen.label) },
                selected = currentRoute == screen.route,
                onClick = {
                    navController.navigate(screen.route)
                }
            )
        }
    }
}

sealed class Navigate(val route: String, val label: String, val icon: ImageVector, show: Boolean) {
    data object Poetry : Navigate("poetry", "Poetry", Icons.Default.FavoriteBorder, true)
    data object Profile : Navigate("profile", "Profile", Icons.Default.Person, true)
    data object Welcome : Navigate("welcome", "Welcome", Icons.Default.Person, false)
    data object AuthorDetail :
        Navigate("detail/{authorName}", "Author Detail", Icons.Default.Person, false)
}