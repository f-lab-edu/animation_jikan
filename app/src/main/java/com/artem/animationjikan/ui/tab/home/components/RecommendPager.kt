package com.artem.animationjikan.ui.tab.home.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage

@Composable
fun RecommendPager(pageCount: Int) {
    val pagerState = rememberPagerState(pageCount = { pageCount })

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

private fun getPageColor(page: Int): Color {
    return when (page % 5) {
        0 -> Color(0xFFEF5350)
        1 -> Color(0xFF66BB6A)
        2 -> Color(0xFF26C6DA)
        3 -> Color(0xFF7E57C2)
        else -> Color(0xFFFFCA28)
    }
}