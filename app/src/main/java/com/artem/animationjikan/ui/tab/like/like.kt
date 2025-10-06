package com.artem.animationjikan.ui.tab.like

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AssistChip
import androidx.compose.material3.AssistChipDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import com.artem.animationjikan.R
import com.artem.animationjikan.data.model.LikeModel
import com.artem.animationjikan.ui.theme.AnimationJikanTheme
import com.artem.animationjikan.util.FILTER_OPTION
import com.artem.animationjikan.util.enums.FilterCategory
import com.artem.animationjikan.util.likeList
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LikeTab() {
    val sheetState = rememberModalBottomSheetState()
    val scope = rememberCoroutineScope()
    val openSheet: () -> Unit = {
        scope.launch { sheetState.show() }
    }

    var selectedCategory by remember { mutableStateOf(FilterCategory.ALL) }

    Scaffold(
        containerColor = Color.Black,
        content = { padding ->
            Box(modifier = Modifier.padding(horizontal = 16.dp)) {
                Column {
                    Box(
                        modifier = Modifier.padding(vertical = 6.dp)
                    ) {
                        AssistChip(
                            onClick = openSheet,
                            label = {
                                Text(
                                    stringResource(selectedCategory.stringRes),
                                    color = Color.White
                                )
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
                        contentPadding = PaddingValues(bottom = 25.dp),
                        modifier = Modifier.fillMaxSize()
                    ) {
                        items(likeList) { item ->
                            GridItem(item)
                        }
                    }

                }
            }

        })

    if (sheetState.isVisible) {
        ModalBottomSheet(
            onDismissRequest = {
                scope.launch { sheetState.hide() }
            },
            sheetState = sheetState
        ) {
            FilterBottomSheetContent(list = FILTER_OPTION) { item ->
                selectedCategory = item
                scope.launch { sheetState.hide() }
            }
        }
    }
}

@Composable
fun FilterBottomSheetContent(list: List<FilterCategory>, onItemClick: (FilterCategory) -> Unit) {
    LazyColumn {
        items(list) { item ->
            TextButton(
                modifier = Modifier.fillMaxWidth(),
                onClick = {
                    onItemClick(item)
                }
            ) {
                Text(
                    text = stringResource(item.stringRes),
                    color = colorResource(R.color.grey4),
                    fontSize = 18.sp,
                    fontWeight = FontWeight.SemiBold,
                    modifier = Modifier.padding(all = 10.dp)
                )
            }
        }
    }
}

@Composable
fun GridItem(model: LikeModel) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box {

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

            IconButton(
                modifier = Modifier.align(Alignment.TopEnd),
                onClick = {}
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_favorite_off),
                    contentDescription = null
                )
            }
        }

        Spacer(modifier = Modifier.height(8.dp))
    }
}

@Composable
@Preview
fun LikePreView() {
    AnimationJikanTheme {
        LikeTab()
    }
}