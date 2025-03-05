package com.example.testwithpoetry.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.testwithpoetry.MainViewModel
import com.example.testwithpoetry.R
import com.example.testwithpoetry.ui.theme.TestWithPoetryTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen() {
    val viewModel: MainViewModel = hiltViewModel()
    val user = viewModel.getUser()

    TestWithPoetryTheme {
        Scaffold(
            modifier = Modifier
                .fillMaxSize()
                .padding(vertical = 16.dp),
            topBar = {
                Box(
                    modifier = Modifier.padding(horizontal = 16.dp)
                ) {
                    TopAppBar(
                        title = { Text("Profile") },
                        navigationIcon = {
                            Icon(
                                painter = painterResource(R.drawable.ic_arrow_back),
                                contentDescription = ""
                            )
                        }
                    )
                }
            },
        ) { innerPadding ->
            Column(
                modifier = Modifier
                    .padding(innerPadding)
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
                user?.let {
                    Text(it.name, fontStyle = FontStyle.Italic, fontSize = 30.sp)
                    Text(it.email,  fontSize = 14.sp, color = Color.DarkGray)
                    Text(it.birthday.toString(), fontStyle = FontStyle.Italic, fontSize = 14.sp, color = Color.DarkGray)
                }
            }

        }
    }
}