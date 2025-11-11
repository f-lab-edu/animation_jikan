package com.artem.animationjikan.presentation.ui.screen.detail.animation.tabs.news

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import coil3.request.crossfade
import com.artem.animationjikan.R

fun LazyListScope.newsTab() {
    item { Spacer(modifier = Modifier.height(10.dp)) }
    items(count = 30, key = { index -> "episode_$index" }) {

        EpisodeItem()
    }
}

@Composable
fun EpisodeItem() {
    Row(modifier = Modifier.padding(vertical = 8.dp, horizontal = 10.dp)) {
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data("https://cdn.myanimelist.net/s/common/uploaded_files/1759433847-4e88065e62cc655729bc4a6fcf70bc24.jpeg?s=9aa7be20e5f99b4b4fcd721ec30d2499")
                .crossfade(true)
                .build(),
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth(0.35f)
                .aspectRatio(120f / 175f)
                .clip(RoundedCornerShape(4.dp))
                .clickable {

                }
                .background(color = Color.LightGray),
            contentScale = ContentScale.Crop,
        )

        Spacer(modifier = Modifier.width(10.dp))

        Column(
            modifier = Modifier.align(Alignment.Top),
            verticalArrangement = Arrangement.Center,
        ) {
            Text(
                "North American Anime & Manga Releases for October",
                fontSize = 18.sp,
                lineHeight = 20.sp,
                fontWeight = FontWeight(600),
                maxLines = 2,
                color = colorResource(R.color.white)
            )
            Spacer(modifier = Modifier.height(3.dp))
            Text(
                "2025-10-02 12:39:00",
                fontSize = 10.sp,
                lineHeight = 17.sp,
                maxLines = 1,
                fontWeight = FontWeight(400),
                color = colorResource(R.color.gray4)
            )
            Spacer(modifier = Modifier.height(15.dp))
            Text(
                "Aiimee",
                fontSize = 12.sp,
                lineHeight = 17.sp,
                maxLines = 1,
                fontWeight = FontWeight(400),
                color = colorResource(R.color.white)
            )
            Spacer(modifier = Modifier.height(5.dp))
            Text(
                "Here are the North American anime, manga, and light novel releases for September. Week 1: October 6 - 13 Anime Releases Odd Taxi Blu-ray Ookami to Koushinryou: Merch..." +
                        "Here are the North American anime, manga, and light novel releases for September. Week 1: October 6 - 13 Anime Releases Odd Taxi Blu-ray Ookami to Koushinryou: Merch...",
                fontSize = 12.sp,
                lineHeight = 17.sp,
                maxLines = 5,
                fontWeight = FontWeight(400),
                color = colorResource(R.color.gray4)
            )
        }

    }
}