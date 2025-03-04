package com.example.testwithpoetry.ui.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.testwithpoetry.MainViewModel
import com.example.testwithpoetry.R
import com.example.testwithpoetry.ui.theme.TestWithPoetryTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AuthorsScreen() {
    val viewModel: MainViewModel = hiltViewModel()

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
                        title = { Text("Welcome ${viewModel.getName()}") },
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
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
            ) {

            }
        }
    }
}