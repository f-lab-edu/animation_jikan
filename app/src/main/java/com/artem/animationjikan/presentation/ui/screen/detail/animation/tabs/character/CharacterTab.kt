package com.artem.animationjikan.presentation.ui.screen.detail.animation.tabs.character

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.FlowRow
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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
import com.artem.animationjikan.domain.entities.AnimeCharacterEntity

@Composable
fun CharacterTab(animeCharacterEntity: AnimeCharacterEntity) {
    Column(
        modifier = Modifier.height(185.dp).padding(all = 10.dp)
    ) {

        Row(modifier = Modifier.fillMaxWidth().fillMaxHeight()) {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(animeCharacterEntity.imageUrl)
                    .crossfade(true)
                    .build(),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth(0.3f)
                    .aspectRatio(120f / 175f)
                    .clip(RoundedCornerShape(4.dp))
                    .background(color = Color.LightGray)
                    .clickable {

                    },
                contentScale = ContentScale.Crop
            )

            Spacer(modifier = Modifier.width(10.dp))

            Column(
                modifier = Modifier.fillMaxHeight(),
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    animeCharacterEntity.characterName,
                    fontSize = 18.sp,
                    lineHeight = 20.sp,
                    fontWeight = FontWeight(600),
                    maxLines = 2,
                    color = colorResource(R.color.white)
                )

                Spacer(modifier = Modifier.height(20.dp))

                FlowRow(
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp),
                    modifier = Modifier.fillMaxWidth(),
                    maxLines = 2
                ) {
                    animeCharacterEntity.actors.forEach { actor ->
                        Row {
                            Box(
                                modifier = Modifier
                                    .size(45.dp)
                            ) {
                                AsyncImage(
                                    model = ImageRequest.Builder(LocalContext.current)
                                        .data(actor.imageUrl)
                                        .crossfade(true)
                                        .build(),
                                    contentDescription = null,
                                    modifier = Modifier
                                        .fillMaxSize()
                                        .clip(CircleShape)
                                        .clickable {

                                        }
                                        .background(color = Color.LightGray),
                                    contentScale = ContentScale.Crop,
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}