package com.example.testwithpoetry.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.testwithpoetry.domain.repository.DatabaseRepository
import com.example.testwithpoetry.domain.models.Author
import com.example.testwithpoetry.domain.usecase.GetAuthorsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthorsViewModelModel @Inject constructor(
    private val getAuthorsUseCase: GetAuthorsUseCase,
    private val databaseRepository: DatabaseRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(AuthorsUiState())
    val uiState: StateFlow<AuthorsUiState> = _uiState.asStateFlow()

    init {
        loadAuthors()
    }

    private fun loadAuthors() {
        viewModelScope.launch {
            getAuthorsUseCase.execute().collect { authors ->
                _uiState.update {
                    it.copy(authors = authors)
                }
            }
        }
    }

    fun saveFavoriteAuthor(authorName: String) {
        viewModelScope.launch {
            databaseRepository.addFavorite(authorName)
        }
    }

}

data class AuthorsUiState(
    val authors: List<Author> = listOf()
)