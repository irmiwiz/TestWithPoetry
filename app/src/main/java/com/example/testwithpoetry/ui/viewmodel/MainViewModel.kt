package com.example.testwithpoetry.ui.viewmodel

import androidx.lifecycle.ViewModel
import com.example.testwithpoetry.data.local.preferences.UserPreferences
import com.example.testwithpoetry.domain.repository.DatabaseRepository
import com.example.testwithpoetry.data.remote.api.PoetryRepository
import com.example.testwithpoetry.domain.models.User
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repo: PoetryRepository,
    private val userPreferences: UserPreferences,
    private val databaseRepository: DatabaseRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(AuthorsUiState())
    val uiState: StateFlow<AuthorsUiState> = _uiState.asStateFlow()


    fun saveUser(user: User) {
        userPreferences.saveUser(user)
    }

    fun getUser() = userPreferences.getUser()
}

data class AuthorsUiState3(
    val authors: List<String> = listOf()
)