package com.artem.animationjikan.presentation.ui.tab.home.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.artem.animationjikan.R
import com.artem.animationjikan.domain.entities.HomeCommonEntity
import com.artem.animationjikan.presentation.ui.components.HeightGap
import com.artem.animationjikan.presentation.ui.components.JikanNetworkCardImage
import com.artem.animationjikan.presentation.ui.components.ShimmerListItem
import com.artem.animationjikan.presentation.ui.theme.AnimationJikanTheme
import com.artem.animationjikan.util.enums.FilterCategory

@Composable
fun ContentSectionRow(
    title: Int,
    list: List<HomeCommonEntity>,
    isLoadingState: Boolean = true,
    onItemClick: (HomeCommonEntity) -> Unit,
    onItemLikeClick: (HomeCommonEntity) -> Unit
) {
    Column {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 2.dp)
        ) {
            Text(
                stringResource(title),
                style = TextStyle(
                    fontSize = 18.sp,
                    color = Color.White,
                    fontWeight = FontWeight.SemiBold
                )
            )
        }
        HeightGap( 4)
        ShimmerListItem(
            isLoading = isLoadingState,
            contentAfterLoading = {
                LazyRow(
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    items(list.size) { index ->
                        val model = list[index]
                        Box {
                            JikanNetworkCardImage(
                                modifier = Modifier
                                    .size(width = 110.dp, height = 159.dp)
                                    .clip(RoundedCornerShape(4.dp))
                                    .background(color = Color.LightGray),
                                onClick = { onItemClick(it) },
                                imageUrl = model.imageUrl,
                                result = model,
                                contentDescription = stringResource(R.string.poster),
                            )

                            IconButton(
                                modifier = Modifier.align(Alignment.TopEnd),
                                onClick = { onItemLikeClick(model) }
                            ) {
                                Icon(
                                    painter = painterResource(id = if (model.likeStatus) R.drawable.ic_favorite_red_on else R.drawable.ic_favorite_white_off),
                                    tint = Color.Unspecified,
                                    contentDescription = null
                                )
                            }

                        }
                    }
                }
            }
        )

    }
}

@Composable
@Preview
fun ContentSectionRowPreview() {
    AnimationJikanTheme {
        ContentSectionRow(
            title = R.string.section_recently_viewed,
            list = listOf(
                HomeCommonEntity(
                    id = 0,
                    type = FilterCategory.ANIMATION,
                )
            ),
            onItemClick = {

            },
            onItemLikeClick = {}
        )
    }
}

