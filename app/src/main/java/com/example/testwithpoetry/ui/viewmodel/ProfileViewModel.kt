package com.example.testwithpoetry.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope

import com.example.testwithpoetry.domain.models.User
import com.example.testwithpoetry.domain.usecase.GetUserUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val getUserUseCase: GetUserUseCase
) : ViewModel() {
    private val _uiState = MutableStateFlow(ProfileUiState())
    val uiState: StateFlow<ProfileUiState> = _uiState.asStateFlow()

    init {
        getUSerData()
    }

    private fun getUSerData() {
        viewModelScope.launch {
            val response = getUserUseCase.execute()

            _uiState.update {
                it.copy(user = response)
            }

        }
    }

}

data class ProfileUiState(
    val user: User? = null
)