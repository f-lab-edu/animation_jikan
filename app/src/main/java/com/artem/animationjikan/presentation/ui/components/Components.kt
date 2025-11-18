package com.artem.animationjikan.presentation.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import coil3.request.crossfade

@Composable
fun HeightGap(height: Int = 1) {
    Spacer(modifier = Modifier.height(height = height.dp))
}

@Composable
fun WidthGap(width: Int = 1) {
    Spacer(modifier = Modifier.width(width = width.dp))
}

@Composable
fun <T> JikanNetworkCardImage(
    modifier: Modifier = Modifier,
    imageUrl: String?,
    result: T? = null,
    onClick: (T) -> Unit? = {},
    contentDescription: String? = null,
) {
    AsyncImage(
        modifier = modifier.clickable { if (result != null) onClick(result) },
        model = ImageRequest.Builder(LocalContext.current)
            .data(imageUrl)
            .crossfade(true)
            .build(),
        contentDescription = contentDescription,
        contentScale = ContentScale.Crop,
    )
}

fun Modifier.defaultCardModifier(fraction: Float = 0.35f): Modifier {
    return this.then(
        Modifier
            .fillMaxWidth(fraction)
            .aspectRatio(120f / 175f)
            .clip(RoundedCornerShape(4.dp))
            .background(color = Color.LightGray)
    )
}