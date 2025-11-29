package com.artem.animationjikan.presentation.ui.screen.detail.animation.detail.animation

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import com.artem.animationjikan.R
import com.artem.animationjikan.domain.entities.HomeCommonEntity
import com.artem.animationjikan.presentation.ui.LocalNavScreenController
import com.artem.animationjikan.presentation.ui.components.LoadingSpinner
import com.artem.animationjikan.presentation.ui.components.showToast
import com.artem.animationjikan.presentation.ui.screen.detail.animation.detail.DetailActions
import com.artem.animationjikan.presentation.ui.screen.detail.animation.detail.DetailTopBar
import com.artem.animationjikan.presentation.ui.screen.detail.animation.detail.DetailUiState
import com.artem.animationjikan.presentation.ui.screen.detail.animation.detail.animation.tabs.character.CharacterTab
import com.artem.animationjikan.presentation.ui.screen.detail.animation.detail.animation.tabs.character.CharacterViewModel
import com.artem.animationjikan.presentation.ui.screen.detail.animation.detail.animation.tabs.news.NewsItem
import com.artem.animationjikan.presentation.ui.screen.detail.animation.detail.animation.tabs.news.NewsViewModel
import com.artem.animationjikan.presentation.ui.screen.detail.animation.detail.animation.tabs.review.ReviewTab
import com.artem.animationjikan.presentation.ui.screen.detail.animation.detail.animation.tabs.review.ReviewViewModel
import com.artem.animationjikan.presentation.ui.screen.detail.animation.detail.detailContent
import com.artem.animationjikan.presentation.ui.screen.detail.animation.detail.detailDefaultGap
import com.artem.animationjikan.presentation.ui.screen.detail.animation.detail.detailImage
import com.artem.animationjikan.presentation.ui.screen.detail.animation.detail.detailScore
import com.artem.animationjikan.presentation.ui.screen.detail.animation.detail.detailTitle
import com.artem.animationjikan.presentation.ui.screen.detail.animation.detail.header
import com.artem.animationjikan.presentation.ui.screen.detail.animation.detail.renderTabContent
import com.artem.animationjikan.presentation.ui.theme.AnimationJikanTheme
import com.artem.animationjikan.util.enums.DetailTabs
import com.artem.animationjikan.util.enums.FilterType
import com.artem.animationjikan.util.enums.ViewModelState
import com.artem.animationjikan.util.event.UiEvent
import com.artem.animationjikan.util.router.NavRoutes
import com.google.gson.Gson
import java.util.Base64


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
            if (event is UiEvent.ShowToast) {
                showToast(context = context, event.message)
            }
        }
    }

    LaunchedEffect(selectedDestination.value) {
        val animationId = animationDetailViewModel.paramEntity?.id ?: 0

        when (selectedDestination.value) {
            DetailTabs.FIRST -> newsViewModel.execute(malId = animationId)
            DetailTabs.SECOND -> reviewViewModel.execute(malId = animationId)
            DetailTabs.THIRD -> characterViewModel.execute(malId = animationId)
        }
    }

    Scaffold(
        containerColor = colorResource(R.color.black),
        topBar = {
            DetailTopBar(
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
                        paddingValues = paddingValues,
                        detailUiState = DetailUiState(
                            detailEntity = animationDetailViewModel.detailEntity,
                            selectedDestination = selectedDestination.value,
                            scrollState = scrollState
                        ),
                        detailActions = DetailActions(
                            onTabClick = { selectedDestination.value = it },
                            onCharacterClick = { entity ->
                                val jsonString = Gson().toJson(entity)
                                val encodedString = Base64.getUrlEncoder()

                                navController.navigate(
                                    "${NavRoutes.CharacterDetail.router}/" + encodedString.encodeToString(
                                        jsonString.toByteArray(
                                            Charsets.UTF_8
                                        )
                                    )
                                )
                            }
                        ),
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


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun AnimationDetailContent(
    paddingValues: PaddingValues,
    detailUiState: DetailUiState,
    detailActions: DetailActions,
    newsViewModel: NewsViewModel = hiltViewModel(),
    reviewViewModel: ReviewViewModel = hiltViewModel(),
    characterViewModel: CharacterViewModel = hiltViewModel(),
) {

    val tabTitles = listOf(R.string.news, R.string.review, R.string.character)

    LazyColumn(
        state = detailUiState.scrollState,
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues)
    ) {

        detailImage(imageUrl = detailUiState.detailEntity.imageUrl)

        detailDefaultGap()

        detailTitle(title = detailUiState.detailEntity.title)

        detailScore(
            icon = R.drawable.ic_star_full,
            score = detailUiState.detailEntity.score.toString()
        )

        detailContent(synopsis = detailUiState.detailEntity.synopsis)

        header(
            onTabClick = detailActions.onTabClick,
            tabTitles = tabTitles,
            selectedDestination = detailUiState.selectedDestination
        )

        detailDefaultGap()

        when (detailUiState.selectedDestination) {
            DetailTabs.FIRST -> {
                renderTabContent(
                    viewModel = newsViewModel,
                    emptyMessageRedId = R.string.no_get_news_data,
                ) { item, _ ->
                    NewsItem(newsEntity = item)
                }
            }

            DetailTabs.SECOND -> {
                renderTabContent(
                    viewModel = reviewViewModel,
                    emptyMessageRedId = R.string.no_get_review_data
                ) { item, _ ->
                    ReviewTab(reviewModel = item)
                }
            }

            DetailTabs.THIRD -> {
                renderTabContent(
                    viewModel = characterViewModel,
                    emptyMessageRedId = R.string.no_get_character_data,
                ) { item, _ ->
                    CharacterTab(
                        animeCharacterEntity = item,
                        onClick = {
                            detailActions.onCharacterClick(
                                HomeCommonEntity(
                                    id = it.malId,
                                    type = FilterType.CHARACTER,
                                    imageUrl = it.imageUrl,
                                    likeStatus = it.likeStatus
                                )
                            )
                        },
                        onHeartClick = {
                            characterViewModel.toggleFavorite(it)
                        }
                    )
                }
            }
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
