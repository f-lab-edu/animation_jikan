package com.artem.animationjikan.presentation.ui.screen.detail.actor

import android.annotation.SuppressLint
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.PrimaryTabRow
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Tab
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import coil3.request.crossfade
import com.artem.animationjikan.R
import com.artem.animationjikan.presentation.ui.LocalNavScreenController
import com.artem.animationjikan.util.enums.DetailTabs

/// 해당 파일은 테스트 용으로 임시로 둔 파일이며, AnimationDetail에서 합쳐질 예정
@Composable
fun ActorDetailScreen() {
    val navController = LocalNavScreenController.current
    val scrollState = rememberLazyListState()

    val showTitle by remember { derivedStateOf { scrollState.firstVisibleItemIndex > 2 } }

    Scaffold(
        containerColor = colorResource(R.color.black),
        topBar = {
            ActorDetailTopBar(
                title = "Sample Animation Title",
                showTitle = showTitle,
                onBackPressed = { navController.popBackStack() },
                onFavoriteClick = { },
            )
        },
        content = { paddingValues ->
            ActorDetailContent(
                scrollState = scrollState,
                paddingValues = paddingValues
            )
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ActorDetailTopBar(
    title: String,
    showTitle: Boolean,
    onBackPressed: () -> Unit,
    onFavoriteClick: () -> Unit
) {

    val containerColor by animateColorAsState(
        targetValue = if (showTitle) colorResource(R.color.black) else Color.Transparent,
        label = "topAppBarContainerColor"
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
                Icon(painter = painterResource(R.drawable.ic_arrow_back), contentDescription = null)
            }
        },
        actions = {
            IconButton(onClick = { onFavoriteClick() }) {
                Icon(
                    painter = painterResource(R.drawable.ic_favorite_off),
                    contentDescription = null
                )
            }
        }
    )
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun ActorDetailContent(
    scrollState: LazyListState,
    paddingValues: PaddingValues
) {
    val selectedDestination = remember { mutableStateOf(DetailTabs.FIRST) }
    val tabTitles = listOf(R.string.news, R.string.review, R.string.character)

    LazyColumn(
        state = scrollState,
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues)
    ) {
        item {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(2.5f / 3f)
            ) {
                AsyncImage(
                    model = "https://cdn.myanimelist.net//images//anime//10//89830.jpg",
                    contentDescription = stringResource(R.string.poster),
                    modifier = Modifier
                        .fillMaxHeight()
                        .align(Alignment.Center)
                        .clip(RoundedCornerShape(12.dp)),
                    contentScale = ContentScale.FillHeight
                )
            }
        }

        item {
            Spacer(modifier = Modifier.height(10.dp))
        }

        item {
            Text(
                modifier = Modifier.padding(horizontal = 10.dp, vertical = 8.dp),
                text = "Sample Animation Title",
                color = colorResource(R.color.white),
                fontSize = 18.sp,
                lineHeight = 20.sp,
                fontWeight = FontWeight(600)
            )
        }

        item {
            Row(
                modifier = Modifier.padding(all = 5.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Image(
                    painter = painterResource(R.drawable.ic_star_full),
                    contentDescription = null
                )
                Spacer(modifier = Modifier.width(5.dp))
                Text(
                    "4.8",
                    fontSize = 14.sp,
                    lineHeight = 18.sp,
                    fontWeight = FontWeight(400),
                    color = colorResource(R.color.white)
                )
            }
        }

        item {
            Column {
                Spacer(modifier = Modifier.height(16.dp))
                ExpandableText(
                    fullText = "The contents of a hidden grave draw the interest of an industrial titan and send officer K, an LAPD blade runner, on a quest to find a missing legend. The contents of a hidden grave draw the interest of an industrial titan and send officer K, an LAPD blade runner, on a quest to find a missing legend.",
                )
                Spacer(modifier = Modifier.height(11.dp))
            }
        }

        stickyHeader {
            PrimaryTabRow(
                selectedTabIndex = 0,
                containerColor = colorResource(R.color.black),
                contentColor = colorResource(R.color.red),
                divider = {
                    Spacer(modifier = Modifier.height(0.dp))
                },
                indicator = {
                    Box(
                        modifier = Modifier
                            .tabIndicatorOffset(selectedDestination.value.ordinal)
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
                        selected = index == tabs.indexOf(selectedDestination.value),
                        selectedContentColor = colorResource(R.color.white),
                        unselectedContentColor = colorResource(R.color.white),
                        onClick = {
                            selectedDestination.value = tab
                        },
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

        when (selectedDestination.value) {
            DetailTabs.FIRST -> items(count = 30, key = { index -> "actor$index" }) {
                Row(modifier = Modifier.padding(vertical = 8.dp, horizontal = 10.dp)) {
                    AsyncImage(
                        model = ImageRequest.Builder(LocalContext.current)
                            .data("https://cdn.myanimelist.net/s/common/uploaded_files/1759433847-4e88065e62cc655729bc4a6fcf70bc24.jpeg?s=9aa7be20e5f99b4b4fcd721ec30d2499")
                            .crossfade(true)
                            .build(),
                        contentDescription = null,
                        modifier = Modifier
                            .fillMaxWidth(0.4f)
                            .aspectRatio(120f / 175f)
                            .clip(RoundedCornerShape(4.dp))
                            .clickable {

                            }
                            .background(color = Color.LightGray),
                        contentScale = ContentScale.Crop,
                    )

                    Spacer(modifier = Modifier.width(10.dp))

                    Column(
                        modifier = Modifier.align(Alignment.Top),
                        verticalArrangement = Arrangement.Center,
                    ) {
                        Text(
                            "제목",
                            fontSize = 18.sp,
                            lineHeight = 20.sp,
                            fontWeight = FontWeight(600),
                            maxLines = 2,
                            color = colorResource(R.color.white)
                        )
                        Spacer(modifier = Modifier.height(3.dp))
                        Text(
                            "Here are the North American anime, manga, and light novel releases for September. Week 1: October 6 - 13 Anime Releases Odd Taxi Blu-ray Ookami to Koushinryou: Merch...",
                            fontSize = 10.sp,
                            lineHeight = 17.sp,
                            maxLines = 1,
                            fontWeight = FontWeight(400),
                            color = colorResource(R.color.gray4)
                        )
                        Spacer(modifier = Modifier.height(15.dp))
                        Text(
                            "역할",
                            fontSize = 18.sp,
                            lineHeight = 20.sp,
                            fontWeight = FontWeight(600),
                            maxLines = 2,
                            color = colorResource(R.color.white)
                        )
                        Spacer(modifier = Modifier.height(5.dp))
                        Text(
                            "Here are the North American anime, manga, and light novel releases for September. Week 1: October 6 - 13 Anime Releases Odd Taxi Blu-ray Ookami to Koushinryou: Merch..." +
                                    "Here are the North American anime, manga, and light novel releases for September. Week 1: October 6 - 13 Anime Releases Odd Taxi Blu-ray Ookami to Koushinryou: Merch...",
                            fontSize = 12.sp,
                            lineHeight = 17.sp,
                            maxLines = 5,
                            fontWeight = FontWeight(400),
                            color = colorResource(R.color.gray4)
                        )
                    }

                }
            }

            DetailTabs.SECOND -> item { /*ReviewTab()*/ }
            DetailTabs.THIRD -> item { /*CharacterTab(animeCharacterEntity = )*/ }
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

@Composable
@Preview
fun ActorDetailScreenPreView() {
    ActorDetailScreen()
}