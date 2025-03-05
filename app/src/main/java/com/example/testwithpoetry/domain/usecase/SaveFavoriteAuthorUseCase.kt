package com.example.testwithpoetry.domain.usecase



interface SaveFavoriteAuthorUseCase {
    suspend fun execute(authorName: String): Boolean
}