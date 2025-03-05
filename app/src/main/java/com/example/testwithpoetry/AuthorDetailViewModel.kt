package com.example.testwithpoetry

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.testwithpoetry.remoteResponses.PoemResponse
import com.example.testwithpoetry.remoteResponses.PoemTitleResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthorDetailViewModel @Inject constructor(
    private val repo: PoetryRepository
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
            val response = repo.getTitlesByAuthor(authorName)
            if (response is NetworkResource.Success) {
                _uiState.update {
                    it.copy(poems = response.data)
                }
            }
        }
    }

    fun getPoem(authorName: String, title: String) {
        viewModelScope.launch {
            val response = repo.getPoem(authorName, title)
            if (response is NetworkResource.Success) {
                _uiState.update {
                    it.copy(poem = response.data.firstOrNull())
                }
            }
        }
    }
}

data class AuthorDetailUiState(
    val poem: PoemResponse? = null,
    val poems: List<PoemTitleResponse>? = null
)