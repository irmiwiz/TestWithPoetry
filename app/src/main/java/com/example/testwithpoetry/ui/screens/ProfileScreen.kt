package com.example.testwithpoetry.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.testwithpoetry.R
import com.example.testwithpoetry.ui.theme.TestWithPoetryTheme
import com.example.testwithpoetry.ui.viewmodel.ProfileViewModel

@Composable
fun ProfileScreen() {
    val viewModel: ProfileViewModel = hiltViewModel()
    val state = viewModel.uiState.collectAsState()

    TestWithPoetryTheme {
        Column(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Image(
                painter = painterResource(R.drawable.ic_writer_avatar),
                contentDescription = "profile picture",
                modifier = Modifier.size(120.dp)
            )
            Spacer(modifier = Modifier.height(8.dp))
            state.value.user?.let {
                Text(it.name, fontStyle = FontStyle.Italic, fontSize = 30.sp)
                Text(it.email, fontSize = 14.sp, color = Color.DarkGray)
                Text(
                    it.birthday.toString(),
                    fontStyle = FontStyle.Italic,
                    fontSize = 14.sp,
                    color = Color.DarkGray
                )
            }
        }
    }
}