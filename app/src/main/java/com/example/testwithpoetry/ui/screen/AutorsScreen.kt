package com.example.testwithpoetry.ui.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.testwithpoetry.MainViewModel
import com.example.testwithpoetry.R
import com.example.testwithpoetry.ui.theme.TestWithPoetryTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AuthorsScreen() {
    val viewModel: MainViewModel = hiltViewModel()
    val uiState by viewModel.uiState.collectAsState()

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
                        title = {
                            Text(
                                "Welcome ${viewModel.getName()}",
                                textAlign = TextAlign.Center
                            )
                        },
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
                items(uiState.authors) {
                    AuthorCard(it)
                }
            }
        }
    }
}

@Composable
fun AuthorCard(author: String) {
    Card(
        modifier = Modifier
            .fillMaxWidth(),
        shape = RectangleShape
    ) {
        Column {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(vertical = 16.dp)
            ) {
                Icon(
                    painter = painterResource(R.drawable.ic_star_outline),
                    contentDescription = null,
                    tint = Color.DarkGray,
                    modifier = Modifier.size(24.dp),
                )
                Spacer(Modifier.width(16.dp))
                Text(
                    text = author,
                    fontSize = 18.sp,
                    color = Color.Black,
                    fontWeight = FontWeight.Medium,
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 1
                )
            }
            Divider(
                color = Color.LightGray,
                thickness = 1.dp
            )
        }
    }
}

@Preview
@Composable
fun PreviewAuthorCard() {
    val info = listOf("Mario", "Juan", "Vicente", "Albert", "Emilio")
    TestWithPoetryTheme {
        LazyColumn {
            items(info) {
                AuthorCard(it)
            }
        }
    }
}