package com.artem.animationjikan.presentation.ui.screen.detail.animation.detail.character

import android.annotation.SuppressLint
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import coil3.compose.AsyncImage
import com.artem.animationjikan.R
import com.artem.animationjikan.domain.entities.DetailEntity
import com.artem.animationjikan.presentation.ui.LocalNavScreenController
import com.artem.animationjikan.presentation.ui.components.ErrorWidget
import com.artem.animationjikan.presentation.ui.components.HeightGap
import com.artem.animationjikan.presentation.ui.components.LoadingSpinner
import com.artem.animationjikan.presentation.ui.components.WidthGap
import com.artem.animationjikan.presentation.ui.components.showToast
import com.artem.animationjikan.presentation.ui.screen.detail.animation.detail.TabBaseViewModel
import com.artem.animationjikan.presentation.ui.screen.detail.animation.detail.animation.ExpandableText
import com.artem.animationjikan.presentation.ui.screen.detail.animation.detail.character.tabs.actor.ActorTab
import com.artem.animationjikan.presentation.ui.screen.detail.animation.detail.character.tabs.actor.ActorViewModel
import com.artem.animationjikan.presentation.ui.screen.detail.animation.detail.character.tabs.character_image.CharacterImageTab
import com.artem.animationjikan.presentation.ui.screen.detail.animation.detail.character.tabs.character_image.CharacterImageViewModel
import com.artem.animationjikan.util.enums.DetailTabs
import com.artem.animationjikan.util.enums.ViewModelState
import com.artem.animationjikan.util.event.UiEvent

@Composable
fun CharacterDetailScreen(
    characterDetailViewModel: CharacterDetailViewModel = hiltViewModel(),
    actorViewModel: ActorViewModel = hiltViewModel(),
    characterImageViewModel: CharacterImageViewModel = hiltViewModel(),
) {
    val context = LocalContext.current
    val navController = LocalNavScreenController.current
    val scrollState = rememberLazyListState()
    val favoriteState by characterDetailViewModel.likeStatus.collectAsState()


    val showTitle by remember { derivedStateOf { scrollState.firstVisibleItemIndex > 2 } }

    val selectedDestination = remember { mutableStateOf(DetailTabs.FIRST) }

    LaunchedEffect(key1 = Unit) {
        characterDetailViewModel.eventFlow.collect { event ->
            when (event) {
                is UiEvent.ShowToast -> showToast(context = context, event.message)
                else -> {}
            }
        }
    }

    LaunchedEffect(selectedDestination.value) {
        val characterId = characterDetailViewModel.paramEntity?.id ?: 0

        when (selectedDestination.value) {
            DetailTabs.FIRST -> actorViewModel.execute(malId = characterId)
            DetailTabs.SECOND -> actorViewModel.execute(malId = characterId)
            DetailTabs.THIRD -> characterImageViewModel.execute(malId = characterId)
        }
    }

    Scaffold(
        containerColor = colorResource(R.color.black),
        topBar = {
            CharacterDetailTopBar(
                title = characterDetailViewModel.detailEntity.title,
                showTitle = showTitle,
                favoriteState = favoriteState,
                onBackPressed = { navController.popBackStack() },
                onFavoriteClick = { /*characterDetailViewModel.toggleFavorite()*/ },
            )
        },
        content = { paddingValues ->
            when (characterDetailViewModel.state) {
                ViewModelState.Idle, ViewModelState.Loading ->
                    LoadingSpinner(modifier = Modifier.fillMaxSize())

                ViewModelState.Success -> {
                    CharacterDetailContent(
                        scrollState = scrollState,
                        paddingValues = paddingValues,
                        detailEntity = characterDetailViewModel.detailEntity,
                        selectedDestination = selectedDestination.value,
                        onTabClick = { selectedDestination.value = it },
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
fun CharacterDetailTopBar(
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
fun CharacterDetailContent(
    scrollState: LazyListState,
    paddingValues: PaddingValues,
    detailEntity: DetailEntity,
    actorViewModel: ActorViewModel = hiltViewModel(),
    characterImageViewModel: CharacterImageViewModel = hiltViewModel(),
    selectedDestination: DetailTabs,
    onTabClick: (DetailTabs) -> Unit,
) {

    val tabTitles = listOf(
        R.string.actor, R.string.image, R.string.appearance_info
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
                    painter = painterResource(R.drawable.ic_favorite_red_on),
                    contentDescription = null
                )
                WidthGap(5)
                Text(
                    detailEntity.score.toInt().toString(),
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
            DetailTabs.FIRST -> {
                this@LazyColumn.renderTabContent(
                    viewModel = actorViewModel,
                    emptyMessageRedId = R.string.no_get_voice_actor_data,
                ) { item, _ ->
                    ActorTab(
                        characterVoiceActorEntity = item,
                        onClick = {},
                        onHeartClick = {}
                    )
                }
            }

            DetailTabs.SECOND -> {
                this@LazyColumn.renderTabContent(
                    viewModel = actorViewModel,
                    emptyMessageRedId = R.string.no_get_news_data,
                ) { item, _ ->
                    ActorTab(
                        characterVoiceActorEntity = item,
                        onClick = {},
                        onHeartClick = {}
                    )
                }
            }

            DetailTabs.THIRD -> {
                this@LazyColumn.renderTabContent(
                    viewModel = characterImageViewModel,
                    emptyMessageRedId = R.string.no_get_character_image_data,
                ) { _, index ->
                    Spacer(modifier = Modifier.height(10.dp))

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        val triple = characterImageViewModel.list.value[index]
                        val rowItems = listOf(triple.first, triple.second, triple.third)

                        rowItems.forEach { imageUrl ->
                            if (imageUrl != null) {
                                Spacer(modifier = Modifier.height(10.dp))

                                CharacterImageTab(imageUrl = imageUrl)
                            } else {
                                Spacer(modifier = Modifier.weight(1f))
                            }
                        }
                    }
                }
            }
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