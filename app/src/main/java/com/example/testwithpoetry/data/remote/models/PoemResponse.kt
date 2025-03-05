package com.example.testwithpoetry.data.remote.models

import kotlinx.serialization.Serializable

@Serializable
data class PoemResponse(
    val title: String,
    val author: String,
    val lines: List<String>,
    val linecount: String
)