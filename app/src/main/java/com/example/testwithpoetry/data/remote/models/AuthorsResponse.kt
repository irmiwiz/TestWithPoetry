package com.example.testwithpoetry.data.remote.models

import kotlinx.serialization.Serializable

@Serializable
data class AuthorsResponse(
    val authors: List<String>
)