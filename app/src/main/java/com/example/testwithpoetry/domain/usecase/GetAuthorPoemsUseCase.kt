package com.example.testwithpoetry.domain.usecase

import com.example.testwithpoetry.domain.models.PoemTitles

interface GetAuthorPoemsUseCase {
    suspend fun execute(authorName: String): PoemTitles?
}