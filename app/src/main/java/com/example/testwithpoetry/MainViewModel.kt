package com.example.testwithpoetry

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.testwithpoetry.data.UserPreferences
import com.example.testwithpoetry.localModels.User
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repo: PoetryRepository,
    private val userPreferences: UserPreferences
) : ViewModel() {

    private val _uiState = MutableStateFlow(AuthorsUiState())
    val uiState: StateFlow<AuthorsUiState> = _uiState.asStateFlow()

    init {
        getAuthors()
    }

    private fun getAuthors() {
        viewModelScope.launch {
            val response = repo.getAuths()

            if (response is NetworkResource.Success) {
                _uiState.update {
                    it.copy(authors = response.data.authors)
                }

                response.data.authors.forEach {
                    println(it)
                }
            }
        }
    }

    fun saveUser(user: User) {
        userPreferences.saveUser(user)
    }

    fun getName() = userPreferences.getUserName()

    fun getUser() = userPreferences.getUser()
}

data class AuthorsUiState(
    val authors: List<String> = listOf()
)