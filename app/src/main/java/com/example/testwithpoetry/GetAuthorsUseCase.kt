package com.example.testwithpoetry

import com.example.testwithpoetry.localModels.Author
import kotlinx.coroutines.flow.Flow

interface GetAuthorsUseCase {
    suspend fun execute(): Flow<List<Author>>
}