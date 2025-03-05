package com.example.testwithpoetry.domain.usecase

import com.example.testwithpoetry.data.local.preferences.UserPreferences
import com.example.testwithpoetry.domain.models.User
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GetUserUseCaseImpl @Inject constructor(
    private val userPreferences: UserPreferences
) : GetUserUseCase {

    override suspend fun execute(): User? {
        return withContext(Dispatchers.IO) {
            try {
                userPreferences.getUser()
            } catch (e: Exception) {
                null
            }
        }
    }
}