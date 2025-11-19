package com.artem.animationjikan.presentation.ui.screen.detail.animation

import android.annotation.SuppressLint
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import coil3.compose.AsyncImage
import com.artem.animationjikan.R
import com.artem.animationjikan.domain.entities.DetailEntity
import com.artem.animationjikan.presentation.ui.LocalNavScreenController
import com.artem.animationjikan.presentation.ui.components.HeightGap
import com.artem.animationjikan.presentation.ui.components.LoadingSpinner
import com.artem.animationjikan.presentation.ui.components.WidthGap
import com.artem.animationjikan.presentation.ui.components.showToast
import com.artem.animationjikan.presentation.ui.screen.detail.animation.tabs.character.CharacterTab
import com.artem.animationjikan.presentation.ui.screen.detail.animation.tabs.character.CharacterViewModel
import com.artem.animationjikan.presentation.ui.screen.detail.animation.tabs.news.NewsItem
import com.artem.animationjikan.presentation.ui.screen.detail.animation.tabs.news.NewsViewModel
import com.artem.animationjikan.presentation.ui.screen.detail.animation.tabs.review.ReviewTab
import com.artem.animationjikan.presentation.ui.screen.detail.animation.tabs.review.ReviewViewModel
import com.artem.animationjikan.presentation.ui.theme.AnimationJikanTheme
import com.artem.animationjikan.util.enums.DetailTabs
import com.artem.animationjikan.util.enums.FilterCategory
import com.artem.animationjikan.util.enums.FilterType
import com.artem.animationjikan.util.enums.ViewModelState
import com.artem.animationjikan.util.event.UiEvent


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun AnimationDetailScreen(
    animationDetailViewModel: AnimationDetailViewModel = hiltViewModel(),
    newsViewModel: NewsViewModel = hiltViewModel(),
    reviewViewModel: ReviewViewModel = hiltViewModel(),
    characterViewModel: CharacterViewModel = hiltViewModel(),
) {
    val context = LocalContext.current
    val navController = LocalNavScreenController.current
    val scrollState = rememberLazyListState()
    val favoriteState by animationDetailViewModel.likeStatus.collectAsState()

    val showTitle by remember { derivedStateOf { scrollState.firstVisibleItemIndex > 2 } }

    val selectedDestination = remember { mutableStateOf(DetailTabs.FIRST) }

    LaunchedEffect(key1 = Unit) {
        animationDetailViewModel.eventFlow.collect { event ->
            when (event) {
                is UiEvent.ShowToast -> showToast(context = context, event.message)
                else -> {}
            }
        }
    }

    LaunchedEffect(selectedDestination.value) {
        val animationId = animationDetailViewModel.paramEntity?.id ?: 0

        when (selectedDestination.value) {
            DetailTabs.FIRST -> newsViewModel.fetchAnimeNews(malId = animationId)
            DetailTabs.SECOND -> reviewViewModel.fetchReviews(malId = animationId)
            DetailTabs.THIRD -> characterViewModel.fetchAnimeCharacters(malId = animationId)
        }
    }

    Scaffold(
        containerColor = colorResource(R.color.black),
        topBar = {
            AnimationDetailTopBar(
                title = animationDetailViewModel.detailEntity.title,
                showTitle = showTitle,
                favoriteState = favoriteState,
                onBackPressed = { navController.popBackStack() },
                onFavoriteClick = { animationDetailViewModel.toggleFavorite() },
            )
        },
        content = { paddingValues ->
            when (animationDetailViewModel.state) {
                ViewModelState.Idle, ViewModelState.Loading ->
                    LoadingSpinner(modifier = Modifier.fillMaxSize())

                ViewModelState.Success -> {
                    AnimationDetailContent(
                        scrollState = scrollState,
                        paddingValues = paddingValues,
                        detailEntity = animationDetailViewModel.detailEntity,
                        selectedDestination = selectedDestination.value,
                        onTabClick = { selectedDestination.value = it },
                        type = animationDetailViewModel.paramEntity?.type
                    )
                }

                ViewModelState.Error -> Box(modifier = Modifier.fillMaxSize()) {
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
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AnimationDetailTopBar(
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

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun AnimationDetailContent(
    type: FilterType?,
    scrollState: LazyListState,
    paddingValues: PaddingValues,
    detailEntity: DetailEntity,
    newsViewModel: NewsViewModel = hiltViewModel(),
    reviewViewModel: ReviewViewModel = hiltViewModel(),
    characterViewModel: CharacterViewModel = hiltViewModel(),
    selectedDestination: DetailTabs,
    onTabClick: (DetailTabs) -> Unit,
) {

    val tabTitles = listOf(
        when (type) {
            FilterType.ANIMATION -> R.string.news
            FilterType.MANGA -> R.string.image
            FilterType.CHARACTER -> R.string.appearance_info
            FilterType.VOICE_ACTOR -> R.string.animation
            else -> R.string.news
        }, R.string.review, R.string.character
    )

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
                    model = detailEntity.imageUrl,
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
            HeightGap(10)
        }

        item {
            Text(
                modifier = Modifier.padding(horizontal = 10.dp, vertical = 8.dp),
                text = detailEntity.title,
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
                WidthGap(5)
                Text(
                    detailEntity.score.toString(),
                    fontSize = 14.sp,
                    lineHeight = 18.sp,
                    fontWeight = FontWeight(400),
                    color = colorResource(R.color.white)
                )
            }
        }

        item {
            Column {
                HeightGap(16)
                ExpandableText(
                    fullText = detailEntity.synopsis,
                )
                HeightGap(11)
            }
        }

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
                        onClick = {
                            onTabClick(tab)
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

        item { HeightGap(10) }

        when (selectedDestination) {

            DetailTabs.FIRST -> when (newsViewModel.state) {
                ViewModelState.Idle, ViewModelState.Loading ->
                    item {
                        LoadingSpinner(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(200.dp)
                        )
                    }


                ViewModelState.Success ->
                    items(
                        count = newsViewModel.newsList.count(),
                        key = { index -> "$index" }) {
                        NewsItem(newsEntity = newsViewModel.newsList[it])
                    }


                ViewModelState.Error -> {

                }
            }

            DetailTabs.SECOND -> when (reviewViewModel.state) {
                ViewModelState.Idle, ViewModelState.Loading ->
                    item {
                        LoadingSpinner(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(200.dp)
                        )
                    }


                ViewModelState.Success ->
                    items(
                        count = reviewViewModel.reviewList.count(),
                        key = { index -> "$index" }) {

                        ReviewTab(reviewModel = reviewViewModel.reviewList[it])
                    }


                ViewModelState.Error -> {

                }
            }

            DetailTabs.THIRD -> when (characterViewModel.state) {
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
                    items(
                        count = characterViewModel.characterList.count(),
                        key = { index -> "$index" }) {
                        CharacterTab(animeCharacterEntity = characterViewModel.characterList[it])
                    }
                }

                ViewModelState.Error -> {

                }
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


@Composable
@Preview
fun AnimationDetailPreview() {
    AnimationJikanTheme {
        AnimationDetailScreen()
    }
}
