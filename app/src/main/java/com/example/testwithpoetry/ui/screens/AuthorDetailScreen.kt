package com.example.testwithpoetry.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.testwithpoetry.ui.viewmodel.AuthorDetailViewModel
import com.example.testwithpoetry.ui.theme.TestWithPoetryTheme

@Composable
fun AuthorDetailScreen(authorName: String) {

    val viewModel: AuthorDetailViewModel = hiltViewModel()
    val state = viewModel.uiState.collectAsState()

    TestWithPoetryTheme {
        state.value.poems?.let {
            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                items(it) {
                    PoemCard(it.title) {
                        viewModel.getPoem(authorName, it.title)
                    }
                }
            }

            if (state.value.poem != null) {
                //aqui abrir el dialogo
            }
        }
    }
}


@Composable
fun PoemCard(title: String, onPoemClicked: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
            .clickable { onPoemClicked.invoke() }
            .background(Color.White),
        shape = RectangleShape
    ) {
        Column {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(vertical = 16.dp)
            ) {
                Text(
                    text = title,
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