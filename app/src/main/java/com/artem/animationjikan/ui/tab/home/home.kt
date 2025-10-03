package com.artem.animationjikan.ui.tab.home

import android.provider.CalendarContract
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.scrollable
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import coil3.request.crossfade
import com.artem.animationjikan.R
import com.artem.animationjikan.data.model.SampleContentSectionItem
import com.artem.animationjikan.ui.tab.home.components.ContentSectionRow
import com.artem.animationjikan.ui.tab.home.components.RecommendPager
import com.artem.animationjikan.ui.theme.AnimationJikanTheme
import com.artem.animationjikan.util.SAMPLE_IMG_URL


@Composable
fun HomeTab(modifier: Modifier = Modifier) {
    val chipItems = listOf(
        "Animation", "Manga", "Character", "Voice Actor"
    )

    /// TODO  PageCount는 API 연동시 해당 Length에 맞게 변경하기
    val pageCount = 5

    val scrollState = rememberScrollState()

    Box(
        modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
            .padding(horizontal = 10.dp)
    ) {
        Column {
            Spacer(modifier = Modifier.height(6.dp))
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
            Spacer(modifier = Modifier.height(16.dp))
            RecommendPager(pageCount = pageCount)

            Spacer(modifier = Modifier.height(25.dp))

            ContentSectionRow(
                stringResource(R.string.section_recently_viewed),
                listOf(
                    SampleContentSectionItem(
                        idx = 1,
                        url = SAMPLE_IMG_URL
                    ),
                    SampleContentSectionItem(
                        idx = 2,
                        url = SAMPLE_IMG_URL
                    ),
                    SampleContentSectionItem(
                        idx = 3,
                        url = SAMPLE_IMG_URL
                    ),
                    SampleContentSectionItem(
                        idx = 4,
                        url = SAMPLE_IMG_URL
                    ),
                    SampleContentSectionItem(
                        idx = 5,
                        url = SAMPLE_IMG_URL
                    ),

                    ),
                onItemClick = { idx ->

                }
            )

            Spacer(modifier = Modifier.height(16.dp))

            //TODO Solution Hard coding
            ContentSectionRow(
                stringResource(R.string.section_upcoming_anime),
                listOf(
                    SampleContentSectionItem(
                        idx = 1,
                        url = SAMPLE_IMG_URL
                    ),
                    SampleContentSectionItem(
                        idx = 2,
                        url = SAMPLE_IMG_URL
                    ),
                    SampleContentSectionItem(
                        idx = 3,
                        url = SAMPLE_IMG_URL
                    ),
                ),
                onItemClick = { idx ->

                }
            )


            Spacer(modifier = Modifier.height(16.dp))

            ContentSectionRow(
                stringResource(R.string.section_top_anime),
                listOf(
                    SampleContentSectionItem(
                        idx = 1,
                        url = SAMPLE_IMG_URL
                    ),
                    SampleContentSectionItem(
                        idx = 2,
                        url = SAMPLE_IMG_URL
                    ),
                    SampleContentSectionItem(
                        idx = 3,
                        url = SAMPLE_IMG_URL
                    ),
                ),
                onItemClick = { idx ->

                }
            )

            Spacer(modifier = Modifier.height(16.dp))

            ContentSectionRow(
                stringResource(R.string.section_top_manga),
                listOf(
                    SampleContentSectionItem(
                        idx = 1,
                        url = SAMPLE_IMG_URL
                    ),
                    SampleContentSectionItem(
                        idx = 1,
                        url = SAMPLE_IMG_URL
                    ),
                    SampleContentSectionItem(
                        idx = 1,
                        url = SAMPLE_IMG_URL
                    ),
                ),
                onItemClick = { idx ->

                }
            )


            Spacer(modifier = Modifier.height(16.dp))

            ContentSectionRow(
                stringResource(R.string.section_top_character),
                listOf(
                    SampleContentSectionItem(
                        idx = 1,
                        url = SAMPLE_IMG_URL,
                    ),
                    SampleContentSectionItem(
                        idx = 1,
                        url = SAMPLE_IMG_URL
                    ),
                    SampleContentSectionItem(
                        idx = 1,
                        url = SAMPLE_IMG_URL
                    ),
                    SampleContentSectionItem(
                        idx = 1,
                        url = SAMPLE_IMG_URL
                    ),
                    SampleContentSectionItem(
                        idx = 1,
                        url = SAMPLE_IMG_URL
                    ),


                    ),
                onItemClick = { idx ->

                }
            )

            Spacer(modifier = Modifier.height(30.dp))


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