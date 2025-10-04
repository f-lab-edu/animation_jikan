package com.artem.animationjikan.ui.tab.like

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AssistChip
import androidx.compose.material3.AssistChipDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import com.artem.animationjikan.R
import com.artem.animationjikan.data.model.LikeModel
import com.artem.animationjikan.ui.theme.AnimationJikanTheme

@Composable
fun LikeTab() {
    val list = listOf(
        R.string.All,
        R.string.animation,
        R.string.manga,
        R.string.actor,
        R.string.character,
    )

    val likeList = listOf(
        LikeModel(
            idx = 1,
            title = "어떤 만화1",
            isFavorite = true,
            imageUrl = "https://cdn.myanimelist.net/images/anime/3/72078.jpg"
        ),
        LikeModel(
            idx = 2,
            title = "어떤 만화2",
            isFavorite = true,
            imageUrl = "https://cdn.myanimelist.net/images/anime/1935/127974.jpg"
        ),
        LikeModel(
            idx = 2,
            title = "어떤 만화3",
            isFavorite = true,
            imageUrl = "https://cdn.myanimelist.net/images/anime/1935/127974.jpg"
        ),
        LikeModel(
            idx = 2,
            title = "어떤 만화4",
            isFavorite = true,
            imageUrl = "https://cdn.myanimelist.net/images/anime/1935/127974.jpg"
        ),
        LikeModel(
            idx = 2,
            title = "어떤 만화5",
            isFavorite = true,
            imageUrl = "https://cdn.myanimelist.net/images/anime/1935/127974.jpg"
        ),
        LikeModel(
            idx = 2,
            title = "어떤 만화6",
            isFavorite = true,
            imageUrl = "https://cdn.myanimelist.net/images/anime/1935/127974.jpg"
        ),
        LikeModel(
            idx = 2,
            title = "어떤 만화7",
            isFavorite = true,
            imageUrl = "https://cdn.myanimelist.net/images/anime/1935/127974.jpg"
        ),
        LikeModel(
            idx = 2,
            title = "어떤 만화8",
            isFavorite = true,
            imageUrl = "https://cdn.myanimelist.net/images/anime/1935/127974.jpg"
        ),
    )
    Scaffold(
        containerColor = Color.Black,
        topBar = {},
        content = { padding ->
            Box(modifier = Modifier.padding(horizontal = 16.dp)) {
                Column {
                    Box(
                        modifier = Modifier.padding(
                            vertical = 6.dp
                        )

                    ) {
                        AssistChip(
                            onClick = {
                                Log.d("", "")
                            },
                            label = {
                                Text("ALL", color = Color.White)
                            },
                            colors = AssistChipDefaults.assistChipColors(
                                leadingIconContentColor = Color.White
                            ),
                            leadingIcon = {
                                Icon(
                                    painter = painterResource(R.drawable.ic_arrow_down),
                                    tint = Color.White,
                                    contentDescription = null
                                )
                            }
                        )
                    }


                    Spacer(modifier = Modifier.height(22.dp))

                    LazyVerticalGrid(
                        columns = GridCells.Fixed(3),
                        verticalArrangement = Arrangement.spacedBy(8.dp),
                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                        modifier = Modifier.fillMaxSize()
                    ) {
                        items(likeList) { item ->
                            GridItem(item)
                        }
                    }
                }
            }

        })
}

@Composable
fun GridItem(model: LikeModel) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        AsyncImage(
            model = model.imageUrl,
            modifier = Modifier
                .aspectRatio(9f / 13f)
                .clip(
                    RoundedCornerShape(4.dp)
                ),
            contentScale = ContentScale.Crop,
            contentDescription = null,
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            model.title, style = TextStyle(
                fontSize = 18.sp,
                color = Color.White
            )
        )

    }
}

@Composable
@Preview
fun LikePreView() {
    AnimationJikanTheme {
        LikeTab()
    }
}