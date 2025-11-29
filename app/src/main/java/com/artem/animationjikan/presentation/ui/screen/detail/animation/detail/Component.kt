package com.artem.animationjikan.presentation.ui.screen.detail.animation.detail

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.PrimaryTabRow
import androidx.compose.material3.Tab
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
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
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import com.artem.animationjikan.R
import com.artem.animationjikan.presentation.ui.components.ErrorWidget
import com.artem.animationjikan.presentation.ui.components.HeightGap
import com.artem.animationjikan.presentation.ui.components.LoadingSpinner
import com.artem.animationjikan.presentation.ui.components.WidthGap
import com.artem.animationjikan.util.enums.DetailTabs
import com.artem.animationjikan.util.enums.ViewModelState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailTopBar(
    title: String,
    showTitle: Boolean,
    favoriteState: Boolean,
    onBackPressed: () -> Unit,
    onFavoriteClick: () -> Unit,
) {
    val containerColor by animateColorAsState(
        targetValue = if (showTitle) colorResource(R.color.black) else Color.Transparent,
    )

    TopAppBar(
        title = {
            AnimatedVisibility(
                visible = showTitle,
                enter = fadeIn(),
                exit = fadeOut()
            ) {
                Text(
                    text = title,
                    color = Color.White,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = containerColor,
            navigationIconContentColor = colorResource(R.color.white),
            actionIconContentColor = colorResource(R.color.white)
        ),
        navigationIcon = {
            IconButton(
                onClick = { onBackPressed() }
            ) {
                Icon(
                    painter = painterResource(R.drawable.ic_arrow_back),
                    contentDescription = null,
                    tint = Color.Unspecified
                )
            }
        },
        actions = {
            IconButton(onClick = { onFavoriteClick() }) {
                Icon(
                    painter = painterResource(if (favoriteState) R.drawable.ic_favorite_red_on else R.drawable.ic_favorite_off),
                    contentDescription = null,
                    tint = Color.Unspecified
                )
            }
        }
    )
}

fun LazyListScope.detailDefaultGap() {
    item { HeightGap(10) }
}

fun LazyListScope.detailImage(imageUrl: String?) {
    item {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(2.5f / 3f)
        ) {
            AsyncImage(
                model = imageUrl,
                contentDescription = stringResource(R.string.poster),
                modifier = Modifier
                    .fillMaxHeight()
                    .align(Alignment.Center)
                    .clip(RoundedCornerShape(12.dp)),
                contentScale = ContentScale.FillHeight
            )
        }
    }
}

fun LazyListScope.detailTitle(title: String) {
    item {
        Text(
            modifier = Modifier.padding(horizontal = 10.dp, vertical = 8.dp),
            text = title,
            color = colorResource(R.color.white),
            fontSize = 18.sp,
            lineHeight = 20.sp,
            fontWeight = FontWeight(600)
        )
    }
}

fun LazyListScope.detailScore(icon: Int, score: String) {
    item {
        Row(
            modifier = Modifier.padding(all = 5.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Image(
                painter = painterResource(icon),
                contentDescription = null
            )
            WidthGap(5)
            Text(
                score,
                fontSize = 14.sp,
                lineHeight = 18.sp,
                fontWeight = FontWeight(400),
                color = colorResource(R.color.white)
            )
        }
    }
}


fun LazyListScope.detailContent(synopsis: String) {
    item {
        Column {
            HeightGap(16)
            ExpandableText(
                fullText = synopsis,
            )
            HeightGap(11)
        }
    }
}

fun <T> LazyListScope.renderTabContent(
    viewModel: TabBaseViewModel<T>,
    emptyMessageRedId: Int,
    itemContent: @Composable (item: T, index: Int) -> Unit,
) {
    when (viewModel.state.value) {
        ViewModelState.Idle, ViewModelState.Loading -> {
            item {
                LoadingSpinner(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp)
                )
            }
        }


        ViewModelState.Success -> {
            val currentList = viewModel.list.value
            if (currentList.isNotEmpty()) {
                items(currentList.count()) { index ->
                    itemContent(currentList[index], index)
                }
            } else {
                item {
                    ErrorWidget(
                        messageStringResId = emptyMessageRedId,
                    )
                }
            }
        }

        ViewModelState.Error -> {
            item {
                Box(modifier = Modifier.fillMaxSize()) {
                    Text(
                        stringResource(R.string.fail_message),
                        modifier = Modifier
                            .align(alignment = Alignment.Center),
                        fontSize = 11.sp,
                        fontWeight = FontWeight(400),
                        textAlign = TextAlign.Center,
                        color = colorResource(R.color.grey4)
                    )
                }
            }
        }
    }
}

fun LazyListScope.header(
    onTabClick: (DetailTabs) -> Unit,
    tabTitles: List<Int>,
    selectedDestination: DetailTabs = DetailTabs.FIRST
) {
    stickyHeader {
        PrimaryTabRow(
            selectedTabIndex = 0,
            containerColor = colorResource(R.color.black),
            contentColor = colorResource(R.color.red),
            divider = {
                HeightGap()
            },
            indicator = {
                Box(
                    modifier = Modifier
                        .tabIndicatorOffset(selectedTabIndex = selectedDestination.ordinal)
                        .height(4.dp)
                        .padding(horizontal = 20.dp)
                        .background(
                            color = colorResource(
                                id = R.color.red
                            )
                        )
                )
            }
        ) {
            val tabs = DetailTabs.entries.toTypedArray()
            tabs.forEachIndexed { index, tab ->
                Tab(
                    selected = index == tabs.indexOf(tab),
                    selectedContentColor = colorResource(R.color.white),
                    unselectedContentColor = colorResource(R.color.white),
                    onClick = { onTabClick(tab) },
                    text = {
                        Text(
                            text = stringResource(tabTitles[index]),
                            maxLines = 2,
                            overflow = TextOverflow.Ellipsis
                        )
                    }
                )
            }

        }
    }
}

@Composable
fun ExpandableText(
    fullText: String,
    minimizedMaxLines: Int = 3
) {
    var expanded by remember { mutableStateOf(false) }

    val maxLines = if (expanded) Int.MAX_VALUE else minimizedMaxLines

    val toggleExpanded: () -> Unit = { expanded = !expanded }

    var isTextClipped by remember { mutableStateOf(false) }

    Column(modifier = Modifier.padding(horizontal = 10.dp)) {
        Text(
            text = fullText,
            maxLines = maxLines,
            onTextLayout = { textLayoutResult ->
                if (!expanded) {
                    isTextClipped = textLayoutResult.hasVisualOverflow
                }
            },
            modifier = Modifier.clickable(onClick = {
                toggleExpanded()
            }),
            color = Color.White
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