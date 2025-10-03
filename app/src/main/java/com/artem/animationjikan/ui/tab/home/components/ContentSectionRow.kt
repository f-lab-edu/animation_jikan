package com.artem.animationjikan.ui.tab.home.components

import androidx.compose.foundation.background
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
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import coil3.request.crossfade
import com.artem.animationjikan.data.model.SampleContentSectionItem
import com.artem.animationjikan.ui.theme.AnimationJikanTheme

@Composable
fun ContentSectionRow(
    title: String,
    list: List<SampleContentSectionItem>,
    onItemClick: (Int) -> Unit
) {
    Column {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 2.dp)
        ) {
            Text(
                title,
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
            items(list.size) {
                AsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(list[it].url)
                        .crossfade(true)
                        .build(),
                    contentDescription = "Poster",
                    modifier = Modifier
                        .size(width = 110.dp, height = 159.dp)
                        .clip(RoundedCornerShape(4  .dp))
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
            title = "최근 본 내역",
            list = listOf(SampleContentSectionItem(idx = 0, url = "")),
            onItemClick = {

            }
        )
    }
}

