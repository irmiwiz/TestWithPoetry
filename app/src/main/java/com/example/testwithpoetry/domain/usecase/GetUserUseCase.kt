package com.example.testwithpoetry.domain.usecase

import com.example.testwithpoetry.domain.models.User

interface GetUserUseCase {
    suspend fun execute(): User?
}