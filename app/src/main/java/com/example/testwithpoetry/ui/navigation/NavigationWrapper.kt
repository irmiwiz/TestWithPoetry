package com.example.testwithpoetry.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.testwithpoetry.data.UserPreferences
import com.example.testwithpoetry.ui.screen.AuthorsScreen
import com.example.testwithpoetry.ui.screen.ProfileScreen
import com.example.testwithpoetry.ui.screen.WelcomeScreen

@Composable
fun NavigationWrapper(userPreferences: UserPreferences) {
    val navController = rememberNavController()
    val startDestination = if (userPreferences.hasUser()) {
        AuthorsScreen
    } else {
        WelcomeScreen
    }
    NavHost(navController, startDestination = startDestination) {
        composable<WelcomeScreen> {
            WelcomeScreen {
                navController.navigate(PoetryScreen)
            }
        }

        composable<PoetryScreen> {
            AuthorsScreen()
        }

        composable<ProfileScreen> {
            ProfileScreen()
        }
    }
}