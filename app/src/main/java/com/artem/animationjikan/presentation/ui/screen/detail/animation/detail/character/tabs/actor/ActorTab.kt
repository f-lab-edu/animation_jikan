package com.artem.animationjikan.presentation.ui.screen.detail.animation.detail.character.tabs.actor

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.artem.animationjikan.R
import com.artem.animationjikan.domain.entities.CharacterVoiceActorEntity
import com.artem.animationjikan.presentation.ui.components.HeightGap
import com.artem.animationjikan.presentation.ui.components.JikanNetworkCardImage
import com.artem.animationjikan.presentation.ui.components.WidthGap
import com.artem.animationjikan.presentation.ui.components.defaultCardModifier

@Composable
fun ActorTab(
    characterVoiceActorEntity: CharacterVoiceActorEntity,
    onClick: (CharacterVoiceActorEntity) -> Unit,
    onHeartClick: (CharacterVoiceActorEntity) -> Unit
) {
    Row(
        modifier = Modifier
            .padding(vertical = 8.dp, horizontal = 10.dp)
            .clickable { onClick(characterVoiceActorEntity) }
    ) {
        Box {

            JikanNetworkCardImage(
                imageUrl = characterVoiceActorEntity.imageUrl,
                modifier = Modifier.defaultCardModifier(),
                result = characterVoiceActorEntity,
            )

            IconButton(
                modifier = Modifier.align(Alignment.TopEnd),
                onClick = {
                    onHeartClick(characterVoiceActorEntity)
                }
            ) {

                Icon(
                    painter = painterResource(id = if (characterVoiceActorEntity.likeStatus) R.drawable.ic_favorite_red_on else R.drawable.ic_favorite_off),
                    tint = Color.Unspecified,
                    contentDescription = null
                )
            }
        }

        WidthGap(10)

        Column(
            modifier = Modifier.align(Alignment.Top),
            verticalArrangement = Arrangement.Center,
        ) {
            Text(
                characterVoiceActorEntity.name,
                fontSize = 18.sp,
                lineHeight = 20.sp,
                fontWeight = FontWeight(600),
                maxLines = 2,
                color = colorResource(R.color.white)
            )

            HeightGap(3)

            Text(
                characterVoiceActorEntity.language,
                fontSize = 14.sp,
                lineHeight = 17.sp,
                maxLines = 1,
                fontWeight = FontWeight(400),
                color = colorResource(R.color.gray4)
            )
        }
    }


}