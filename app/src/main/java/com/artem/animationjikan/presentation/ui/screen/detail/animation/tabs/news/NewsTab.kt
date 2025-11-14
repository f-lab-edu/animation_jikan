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
import com.artem.animationjikan.domain.entities.NewsEntity


@Composable
fun NewsItem(newsEntity: NewsEntity) {
    Row(modifier = Modifier.padding(vertical = 8.dp, horizontal = 10.dp)) {
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(newsEntity.imageUrl)
                .crossfade(true)
                .build(),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxWidth(0.35f)
                .aspectRatio(120f / 175f)
                .clip(RoundedCornerShape(4.dp))
                .background(color = Color.LightGray)
                .clickable {

                },
        )

        Spacer(modifier = Modifier.width(10.dp))

        Column(
            modifier = Modifier.align(Alignment.Top),
            verticalArrangement = Arrangement.Center,
        ) {
            Text(
                newsEntity.title,
                fontSize = 18.sp,
                lineHeight = 20.sp,
                fontWeight = FontWeight(600),
                maxLines = 2,
                color = colorResource(R.color.white)
            )
            Spacer(modifier = Modifier.height(3.dp))
            // date format yyyy.MM.dd 적용하기
            Text(
                newsEntity.date,
                fontSize = 10.sp,
                lineHeight = 17.sp,
                maxLines = 1,
                fontWeight = FontWeight(400),
                color = colorResource(R.color.gray4)
            )
            Spacer(modifier = Modifier.height(15.dp))
            Text(
                newsEntity.authorUsername,
                fontSize = 12.sp,
                lineHeight = 17.sp,
                maxLines = 1,
                fontWeight = FontWeight(400),
                color = colorResource(R.color.white)
            )
            Spacer(modifier = Modifier.height(5.dp))
            Text(
                newsEntity.excerpt,
                fontSize = 12.sp,
                lineHeight = 17.sp,
                maxLines = 5,
                fontWeight = FontWeight(400),
                color = colorResource(R.color.gray4)
            )
        }

    }
}