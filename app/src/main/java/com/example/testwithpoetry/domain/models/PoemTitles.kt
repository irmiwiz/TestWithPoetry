package com.example.testwithpoetry.domain.models

import com.example.testwithpoetry.data.remote.models.PoemTitleResponse

data class PoemTitles(
    val titles: List<String>
)

fun List<PoemTitleResponse>.toDomain(): PoemTitles {
    return PoemTitles(
        titles = this.map { it.title }
    )
}