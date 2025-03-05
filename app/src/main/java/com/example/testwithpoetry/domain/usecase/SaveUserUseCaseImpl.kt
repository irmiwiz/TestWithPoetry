package com.example.testwithpoetry.domain.usecase

import com.example.testwithpoetry.data.local.preferences.UserPreferences
import com.example.testwithpoetry.domain.models.User
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class SaveUserUseCaseImpl @Inject constructor(
    private val userPreferences: UserPreferences
) : SaveUserUseCase{

    override suspend fun execute(user: User): Boolean {
        return withContext(Dispatchers.IO) {
            try {
                userPreferences.saveUser(user)
                true
            } catch (e: Exception) {
                false
            }
        }
    }
}