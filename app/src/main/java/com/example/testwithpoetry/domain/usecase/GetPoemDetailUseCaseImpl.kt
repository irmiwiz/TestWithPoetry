package com.example.testwithpoetry.domain.usecase

import com.example.testwithpoetry.data.network.NetworkResource
import com.example.testwithpoetry.data.remote.api.PoetryRepository
import com.example.testwithpoetry.domain.models.Poem
import com.example.testwithpoetry.domain.models.toDomain
import javax.inject.Inject

class GetPoemDetailUseCaseImpl @Inject constructor(
    private val repository: PoetryRepository

) : GetPoemDetailUseCase {
    override suspend fun execute(authorName: String, title: String): Poem? {
        return when (val response = repository.getPoem(authorName, title)) {
            is NetworkResource.Success -> response.data.firstOrNull()?.toDomain()
            is NetworkResource.Fail -> null
        }
    }
}