package com.artem.animationjikan.ui.tab

import androidx.compose.foundation.Indication
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.SuggestionChip
import androidx.compose.material3.SuggestionChipDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage

@Composable
fun HomeTab(modifier: Modifier = Modifier) {
    val chipItems = listOf(
        "Animation", "Manga", "Character", "Voice Actor"
    )

    /// TODO  PageCount는 API 연동시 해당 Length에 맞게 변경하기
    val pageCount = 5
    val pagerState = rememberPagerState(pageCount = { pageCount })

    Box(
        modifier
            .fillMaxSize()
            .padding(horizontal = 10.dp)
    ) {
        Column {
            LazyRow(
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(chipItems) { item ->
                    SuggestionChip(
                        onClick = {},
                        label = {
                            Text(item)
                        },
                        colors = SuggestionChipDefaults.suggestionChipColors(
                            containerColor = Color.Black,
                            labelColor = Color.White
                        )
                    )
                }
            }

            HorizontalPager(
                state = pagerState
            ) { page ->
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight(0.6f)
                        .background(color = getPageColor(page)),
                    contentAlignment = Alignment.Center
                ) {
                    AsyncImage(
                        model = "https://cdn.myanimelist.net/images/anime/1015/138006.jpg",
                        contentDescription = "Poster",
                        modifier = Modifier
                            .fillMaxHeight()
                            .aspectRatio(0.75f),
                        contentScale = ContentScale.Crop
                    )
                }
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        vertical = 16.dp
                    ),
                horizontalArrangement = Arrangement.Center
            ) {
                repeat(pageCount) { iteration ->
                    val color = if (pagerState.currentPage == iteration)
                        Color(0xFFE50913) else Color.LightGray

                    Box(
                        modifier = Modifier
                            .padding(horizontal = 4.dp)
                            .size(8.dp)
                            .background(color, CircleShape)
                    )
                }
            }
        }
    }
}

private fun getPageColor(page: Int): Color {
    return when (page % 5) {
        0 -> Color(0xFFEF5350)
        1 -> Color(0xFF66BB6A)
        2 -> Color(0xFF26C6DA)
        3 -> Color(0xFF7E57C2)
        else -> Color(0xFFFFCA28)
    }
}

@Composable
@Preview
fun HomePreView() {
    HomeTab(Modifier.fillMaxSize())
}