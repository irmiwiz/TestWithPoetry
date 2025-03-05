package com.example.testwithpoetry.domain.usecase

import com.example.testwithpoetry.data.network.NetworkResource
import com.example.testwithpoetry.data.remote.api.PoetryRepository
import com.example.testwithpoetry.domain.models.PoemTitles
import com.example.testwithpoetry.domain.models.toDomain
import javax.inject.Inject

class GetAuthorPoemsUseCaseImpl @Inject constructor(
    private val repository: PoetryRepository
) : GetAuthorPoemsUseCase {
    override suspend fun execute(authorName: String): PoemTitles? {
        return when (val response = repository.getTitlesByAuthor(authorName)) {
            is NetworkResource.Success -> response.data.toDomain()
            is NetworkResource.Fail -> null
        }
    }
}