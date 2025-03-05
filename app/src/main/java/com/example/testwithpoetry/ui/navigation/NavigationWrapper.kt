package com.example.testwithpoetry.ui.navigation

import androidx.compose.foundation.layout.Box
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.example.testwithpoetry.R
import com.example.testwithpoetry.data.UserPreferences
import com.example.testwithpoetry.ui.screen.AuthorDetailScreen
import com.example.testwithpoetry.ui.screen.AuthorsScreen
import com.example.testwithpoetry.ui.screen.ProfileScreen
import com.example.testwithpoetry.ui.screen.WelcomeScreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NavigationWrapper(userPreferences: UserPreferences) {
    val navController = rememberNavController()
    var title by remember { mutableStateOf("") }

    val startDestination = if (userPreferences.hasUser()) {
        title = "Welcome ${userPreferences.getUserName()}"
        BottomNavScreen.Authors.route
    } else {
        "welcome"
    }

    Scaffold(
        bottomBar = {
            val currentDestination =
                navController.currentBackStackEntryAsState().value?.destination?.route
            if (currentDestination in listOf(
                    BottomNavScreen.Authors.route,
                    BottomNavScreen.Profile.route
                )
            ) {
                BottomNavigationBar(navController)
            }
        },
        topBar = {
            if (title.isNotBlank()) {
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
            composable("welcome") {
                WelcomeScreen {
                    title = "Welcome ${userPreferences.getUserName()}"
                    navController.navigate(BottomNavScreen.Authors.route) {
                        popUpTo("welcome") { inclusive = true }
                    }
                }
            }

            composable(BottomNavScreen.Authors.route) {
                AuthorsScreen { authorName ->
                    title = authorName
                    navController.navigate("detail/$authorName")
                }
            }

            composable(BottomNavScreen.Profile.route) {
                ProfileScreen()
            }

            composable("detail/{authorName}") { backStackEntry ->
                val authorName = backStackEntry.arguments?.getString("authorName") ?: ""
                AuthorDetailScreen(authorName)
            }
        }
    }
}

@Composable
fun BottomNavigationBar(navController: NavController) {
    val items = listOf(
        BottomNavScreen.Authors,
        BottomNavScreen.Profile
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
                    navController.navigate(screen.route) {
                        popUpTo(BottomNavScreen.Authors.route) { inclusive = false }
                        launchSingleTop = true
                    }
                }
            )
        }
    }
}

sealed class BottomNavScreen(val route: String, val label: String, val icon: ImageVector) {
    object Authors : BottomNavScreen("authors", "Authors", Icons.Default.FavoriteBorder)
    object Profile : BottomNavScreen("profile", "Profile", Icons.Default.Person)
}