package com.example.testwithpoetry.domain.usecase

import com.example.testwithpoetry.domain.repository.DatabaseRepository
import com.example.testwithpoetry.data.network.NetworkResource
import com.example.testwithpoetry.data.remote.api.PoetryRepository
import com.example.testwithpoetry.domain.models.Author
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetAuthorsUseCaseImpl @Inject constructor(
    private val poetryRepository: PoetryRepository,
    private val databaseRepository: DatabaseRepository
) : GetAuthorsUseCase {
    override suspend fun execute(): Flow<List<Author>> {
        val apiAuthorsFlow = flow {
            val response = poetryRepository.getAuths()
            if (response is NetworkResource.Success) {
                emit(response.data.authors.map { Author(name = it) })
            } else {
                emit(emptyList())
            }
        }

        val favoriteAuthorsFlow = flow {
            emit(databaseRepository.getFavorites().map { it.name })
        }

        return apiAuthorsFlow.combine(favoriteAuthorsFlow) { apiAuthors, favoriteAuthors ->
            apiAuthors.map { author ->
                author.copy(liked = favoriteAuthors.contains(author.name))
            }
        }
    }
}