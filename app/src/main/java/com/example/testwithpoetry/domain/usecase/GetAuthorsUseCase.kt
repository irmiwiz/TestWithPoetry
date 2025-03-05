package com.example.testwithpoetry.domain.usecase

import com.example.testwithpoetry.domain.models.Author
import kotlinx.coroutines.flow.Flow

interface GetAuthorsUseCase {
    suspend fun execute(): Flow<List<Author>>
}