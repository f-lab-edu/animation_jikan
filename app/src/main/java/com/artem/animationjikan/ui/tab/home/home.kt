package com.artem.animationjikan.ui.tab.home

import android.provider.CalendarContract
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.SuggestionChip
import androidx.compose.material3.SuggestionChipDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import coil3.request.crossfade
import com.artem.animationjikan.ui.tab.home.components.RecommendPager
import com.artem.animationjikan.ui.theme.AnimationJikanTheme

@Composable
fun HomeTab(modifier: Modifier = Modifier) {
    val chipItems = listOf(
        "Animation", "Manga", "Character", "Voice Actor"
    )

    /// TODO  PageCount는 API 연동시 해당 Length에 맞게 변경하기
    val pageCount = 5

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

            RecommendPager(pageCount = pageCount)

            Spacer(modifier = Modifier.height(25.dp))

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 2.dp)
            ) {
                Text(
                    "최근 본 내역",
                    style = TextStyle(
                        fontSize = 18.sp,
                        color = Color.White,
                        fontWeight = FontWeight.SemiBold
                    )
                )
            }
            Spacer(modifier = Modifier.height(4.dp))

            LazyRow(
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(10) {
                    AsyncImage(
                        model = ImageRequest.Builder(LocalContext.current)
                            .data("https://cdn.myanimelist.net/images/anime/1015/138006.jpg")
                            .crossfade(true)
                            .build(),
                        contentDescription = "Poster",
                        modifier = Modifier
                            .size(width = 110.dp, height = 159.dp)
                            .clip(RoundedCornerShape(4.dp))
                            .background(color = Color.LightGray),
                        contentScale = ContentScale.Crop,
                    )
                }
            }

        }
    }
}


@Composable
@Preview
fun HomePreView() {
    AnimationJikanTheme {
        HomeTab(Modifier.fillMaxSize())
    }
}