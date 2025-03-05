package com.example.testwithpoetry.domain.usecase

import com.example.testwithpoetry.domain.repository.DatabaseRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class SaveFavoriteAuthorUseCaseImpl  @Inject constructor(
    private val databaseRepository: DatabaseRepository
): SaveFavoriteAuthorUseCase {

    override suspend fun execute(authorName: String): Boolean {
        return withContext(Dispatchers.IO) {
            try {
                databaseRepository.addFavorite(authorName)
                true
            } catch (e: Exception) {
                false
            }
        }
    }
}