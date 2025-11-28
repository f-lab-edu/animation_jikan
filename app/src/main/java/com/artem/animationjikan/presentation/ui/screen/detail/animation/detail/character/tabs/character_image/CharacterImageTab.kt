package com.artem.animationjikan.presentation.ui.screen.detail.animation.detail.character.tabs.character_image

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.artem.animationjikan.presentation.ui.components.JikanNetworkCardImage

@Composable
fun CharacterImageTab(
    imageUrl: String,
) {
    JikanNetworkCardImage<String>(
        imageUrl = imageUrl,
        modifier = Modifier
            .height(175.dp)
            .aspectRatio(120f / 175f)
            .clip(RoundedCornerShape(4.dp))
            .background(color = Color.LightGray),
    )
}