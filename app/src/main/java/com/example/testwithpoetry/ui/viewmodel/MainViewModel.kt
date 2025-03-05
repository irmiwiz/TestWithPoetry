package com.example.testwithpoetry.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.testwithpoetry.domain.models.User
import com.example.testwithpoetry.domain.usecase.SaveUserUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val saveUserUseCase: SaveUserUseCase
) : ViewModel() {

     fun saveUser(user: User) {
         viewModelScope.launch {
             saveUserUseCase.execute(user)
         }
    }
}