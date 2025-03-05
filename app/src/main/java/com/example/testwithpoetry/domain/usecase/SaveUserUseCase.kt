package com.example.testwithpoetry.domain.usecase

import com.example.testwithpoetry.domain.models.Author
import kotlinx.coroutines.flow.Flow

interface SaveUserUseCase {
    suspend fun execute(): Flow<List<Author>>
}