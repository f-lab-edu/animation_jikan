package com.artem.animationjikan.presentation.ui.tab.home.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
import com.artem.animationjikan.domain.entities.HomeCommonEntity
import com.artem.animationjikan.presentation.ui.theme.AnimationJikanTheme
import com.artem.animationjikan.util.enums.FilterCategory

@Composable
fun ContentSectionRow(
    title: Int,
    list: List<HomeCommonEntity>,
    onItemClick: (Int) -> Unit
) {
    Column {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 2.dp)
        ) {
            Text(
                stringResource(title),
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
            items(list.size) { index ->
                val model = list[index]
                AsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(model.imageUrl)
                        .crossfade(true)
                        .build(),
                    contentDescription = "Poster",
                    modifier = Modifier
                        .size(width = 110.dp, height = 159.dp)
                        .clip(RoundedCornerShape(4.dp))
                        .clickable {
                            onItemClick(model.id)
                        }
                        .background(color = Color.LightGray),
                    contentScale = ContentScale.Crop,
                )
            }
        }
    }
}

@Composable
@Preview
fun ContentSectionRowPreview() {
    AnimationJikanTheme {
        ContentSectionRow(
            title = R.string.section_recently_viewed,
            list = listOf(
                HomeCommonEntity(
                    id = 0,
                    type = FilterCategory.ANIMATION,
                )
            ),
            onItemClick = {

            }
        )
    }
}

