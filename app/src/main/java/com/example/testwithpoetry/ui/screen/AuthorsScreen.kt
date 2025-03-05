package com.example.testwithpoetry.ui.screen

import androidx.compose.foundation.clickable
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
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.testwithpoetry.MainViewModel
import com.example.testwithpoetry.R
import com.example.testwithpoetry.ui.theme.TestWithPoetryTheme

@Composable
fun AuthorsScreen(
    gotoDetail: (String) -> Unit
) {
    val viewModel: MainViewModel = hiltViewModel()
    val uiState by viewModel.uiState.collectAsState()

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
    ) {
        items(uiState.authors) {
            AuthorCard(it, gotoDetail)
        }
    }
}


@Composable
fun AuthorCard(author: String, openDetail: (String) -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { openDetail(author) },
        shape = RectangleShape
    ) {
        var liked by remember { mutableStateOf(false) }
        Column {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(vertical = 16.dp)
            ) {
                Icon(
                    painter = if (liked) painterResource(R.drawable.ic_star_filled) else painterResource(
                        R.drawable.ic_star_outline
                    ),
                    contentDescription = null,
                    tint = if (liked) Color.Yellow else Color.DarkGray,
                    modifier = Modifier
                        .size(24.dp)
                        .clickable {
                            liked = liked.not()
                        },
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
                AuthorCard(it) {}
            }
        }
    }
}