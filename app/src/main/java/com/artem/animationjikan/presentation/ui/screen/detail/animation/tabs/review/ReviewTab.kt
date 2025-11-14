package com.artem.animationjikan.presentation.ui.screen.detail.animation.tabs.review

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import coil3.request.crossfade
import com.artem.animationjikan.R
import com.artem.animationjikan.domain.entities.ReviewEntity

@Composable
fun ReviewTab(reviewModel: ReviewEntity) {
    Column(modifier = Modifier.padding(16.dp)) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(reviewModel.userProfileUrl)
                    .crossfade(true)
                    .build(),
                contentDescription = null,
                modifier = Modifier
                    .size(40.dp)
                    .clip(CircleShape)
                    .background(color = Color.LightGray),
                contentScale = ContentScale.Crop,
            )

            Spacer(modifier = Modifier.width(12.dp))

            Text(
                reviewModel.username,
                color = Color.White,
                fontSize = 15.sp,
                fontWeight = FontWeight(600),
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.weight(1f))

            RatingBar(rating = reviewModel.score)

        }

        Spacer(modifier = Modifier.height(20.dp))

        ExpandableText(
            fullText = reviewModel.review
        )
    }
}


@Composable
fun RatingBar(rating: Int) {
    val starPainter = painterResource(id = R.drawable.ic_star_full)

    repeat(5) { index ->
        val isFilled = index < rating
        Icon(
            painter = starPainter,
            contentDescription = null,
            modifier = Modifier.size(16.dp),
            tint = if (isFilled) Color.Yellow else Color.LightGray
        )
    }
}

@Composable
fun ExpandableText(
    fullText: String,
    minimizedMaxLines: Int = 5,
) {
    var expanded by remember { mutableStateOf(false) }

    val maxLines = if (expanded) Int.MAX_VALUE else minimizedMaxLines

    val toggleExpanded: () -> Unit = { expanded = !expanded }

    var isTextClipped by remember { mutableStateOf(false) }

    Column {
        Text(
            text = fullText,
            modifier = Modifier.clickable(onClick = { toggleExpanded() }),
            maxLines = maxLines,
            color = colorResource(R.color.gray4),
            fontWeight = FontWeight(400),
            fontSize = 12.sp,
            lineHeight = 20.sp,
            onTextLayout = { textLayoutResult ->
                if (!expanded) {
                    isTextClipped = textLayoutResult.hasVisualOverflow
                }
            },
        )

        if (isTextClipped || expanded) {
            Text(
                text = if (expanded) stringResource(R.string.less) else stringResource(R.string.more),
                modifier = Modifier.clickable(onClick = toggleExpanded),
                color = colorResource(R.color.TransparencyWhite),
                style = MaterialTheme.typography.labelMedium.copy(fontWeight = FontWeight.Bold)
            )
        }
    }
}