package com.example.testwithpoetry.domain.usecase

import com.example.testwithpoetry.domain.models.Poem

interface GetPoemDetailUseCase {
    suspend fun execute(authorName: String, title: String): Poem?
}