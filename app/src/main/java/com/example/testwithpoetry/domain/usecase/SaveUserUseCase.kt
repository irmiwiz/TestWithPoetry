package com.example.testwithpoetry.domain.usecase

import com.example.testwithpoetry.domain.models.User

interface SaveUserUseCase {
    suspend fun execute(user: User): Boolean
}