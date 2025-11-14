package com.artem.animationjikan.presentation.ui.screen.detail.animation.tabs.character

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
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
import com.artem.animationjikan.presentation.ui.components.WidthGap

@Composable
fun CharacterTab(animeCharacterEntity: AnimeCharacterEntity) {
    Row(
        modifier = Modifier.height(175.dp).padding(all = 10.dp)
    ) {
        JikanNetworkCardImage(
            imageUrl = animeCharacterEntity.imageUrl,
            modifier = Modifier
                .height(175.dp)
                .aspectRatio(120f / 175f)
                .clip(RoundedCornerShape(4.dp))
                .background(color = Color.LightGray),
            result = animeCharacterEntity,
            onClick = {

            },
        )

        WidthGap(10)

        Column(
            modifier = Modifier.fillMaxHeight().height(175.dp),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                animeCharacterEntity.characterName,
                fontSize = 18.sp,
                fontWeight = FontWeight(600),
                maxLines = 2,
                color = colorResource(R.color.white)
            )

            HeightGap(20)

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