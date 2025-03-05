package com.example.testwithpoetry.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.testwithpoetry.domain.models.Poem
import com.example.testwithpoetry.domain.models.PoemTitles
import com.example.testwithpoetry.domain.usecase.GetAuthorPoemsUseCase
import com.example.testwithpoetry.domain.usecase.GetPoemDetailUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthorDetailViewModel @Inject constructor(
    private val getPoemUseCase: GetPoemDetailUseCase,
    private val getAuthorPoemsUseCaseImpl: GetAuthorPoemsUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(AuthorDetailUiState())
    val uiState: StateFlow<AuthorDetailUiState> = _uiState.asStateFlow()

    init {
        getPoemsByAuthor(
            "Amy Levy"
        )
    }

    private fun getPoemsByAuthor(authorName: String) {
        viewModelScope.launch {
            val response = getAuthorPoemsUseCaseImpl.execute(authorName)

            _uiState.update {
                it.copy(poems = response)
            }

        }
    }

    fun getPoem(authorName: String, title: String) {
        _uiState.update {
            it.copy(loading = true)
        }

        viewModelScope.launch {
            val response = getPoemUseCase.execute(authorName, title)
            _uiState.update {
                it.copy(poem = response, loading = false)
            }
        }
    }

    fun dismissDialog() {
        _uiState.update { it.copy(poem = null) }
    }
}

data class AuthorDetailUiState(
    val poem: Poem? = null,
    val poems: PoemTitles? = null,
    val loading: Boolean = false
)