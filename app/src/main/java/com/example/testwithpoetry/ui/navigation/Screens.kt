package com.example.testwithpoetry.ui.navigation

import kotlinx.serialization.Serializable

@Serializable
object WelcomeScreen

@Serializable
object PoetryScreen

@Serializable
data class DetailScreen(val authorName: String)

@Serializable
object ProfileScreen