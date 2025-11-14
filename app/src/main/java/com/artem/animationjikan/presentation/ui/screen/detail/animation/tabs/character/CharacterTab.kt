package com.artem.animationjikan.presentation.ui.screen.detail.animation.tabs.character

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.artem.animationjikan.R
import com.artem.animationjikan.domain.entities.AnimeCharacterEntity
import com.artem.animationjikan.presentation.ui.components.HeightGap
import com.artem.animationjikan.presentation.ui.components.JikanNetworkCardImage
import com.artem.animationjikan.presentation.ui.components.defaultCardModifier

@Composable
fun CharacterTab(animeCharacterEntity: AnimeCharacterEntity) {
    Column(
        modifier = Modifier
            .height(185.dp)
            .padding(all = 10.dp)
    ) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
        ) {
            JikanNetworkCardImage(
                imageUrl = animeCharacterEntity.imageUrl,
                modifier = Modifier.defaultCardModifier(),
                result = animeCharacterEntity,
                onClick = {

                },
            )

            HeightGap(height = 10)

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

                HeightGap(height = 20)

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
                                JikanNetworkCardImage(
                                    modifier = Modifier
                                        .fillMaxSize()
                                        .clip(CircleShape)
                                        .background(color = Color.LightGray),
                                    imageUrl = actor.imageUrl,
                                    result = actor,
                                    onClick = {

                                    },
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}