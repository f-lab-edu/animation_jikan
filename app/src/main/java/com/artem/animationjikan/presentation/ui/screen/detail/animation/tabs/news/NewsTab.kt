package com.artem.animationjikan.presentation.ui.screen.detail.animation.tabs.news

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.artem.animationjikan.R
import com.artem.animationjikan.domain.entities.NewsEntity
import com.artem.animationjikan.presentation.ui.components.HeightGap
import com.artem.animationjikan.presentation.ui.components.JikanNetworkCardImage
import com.artem.animationjikan.presentation.ui.components.defaultCardModifier


@Composable
fun NewsItem(newsEntity: NewsEntity) {
    Row(modifier = Modifier.padding(vertical = 8.dp, horizontal = 10.dp)) {
        JikanNetworkCardImage(
            imageUrl = newsEntity.imageUrl,
            modifier = Modifier.defaultCardModifier(),
            result = newsEntity,
            onClick = { _ ->

            }
        )

        HeightGap(height = 10)

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
            HeightGap(height = 3)
            // date format yyyy.MM.dd 적용하기
            Text(
                newsEntity.date,
                fontSize = 10.sp,
                lineHeight = 17.sp,
                maxLines = 1,
                fontWeight = FontWeight(400),
                color = colorResource(R.color.gray4)
            )
            HeightGap(height = 15)
            Text(
                newsEntity.authorUsername,
                fontSize = 12.sp,
                lineHeight = 17.sp,
                maxLines = 1,
                fontWeight = FontWeight(400),
                color = colorResource(R.color.white)
            )
            HeightGap(height = 5)
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