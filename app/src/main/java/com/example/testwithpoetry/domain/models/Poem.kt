package com.example.testwithpoetry.domain.models

import com.example.testwithpoetry.data.remote.models.PoemResponse

data class Poem(
    val author: String,
    val title: String,
    val lines: List<String>
)

fun PoemResponse.toDomain(): Poem {
    return Poem(
        title = this.title,
        author = this.author,
        lines = this.lines
    )
}