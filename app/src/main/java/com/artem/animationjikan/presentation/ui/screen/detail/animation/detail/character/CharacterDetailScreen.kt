package com.artem.animationjikan.presentation.ui.screen.detail.animation.detail.character

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import com.artem.animationjikan.R
import com.artem.animationjikan.presentation.ui.LocalNavScreenController
import com.artem.animationjikan.presentation.ui.components.LoadingSpinner
import com.artem.animationjikan.presentation.ui.components.showToast
import com.artem.animationjikan.presentation.ui.screen.detail.animation.detail.DetailTopBar
import com.artem.animationjikan.presentation.ui.screen.detail.animation.detail.DetailUiState
import com.artem.animationjikan.presentation.ui.screen.detail.animation.detail.character.tabs.actor.ActorTab
import com.artem.animationjikan.presentation.ui.screen.detail.animation.detail.character.tabs.actor.ActorViewModel
import com.artem.animationjikan.presentation.ui.screen.detail.animation.detail.character.tabs.character_image.CharacterImageTab
import com.artem.animationjikan.presentation.ui.screen.detail.animation.detail.character.tabs.character_image.CharacterImageViewModel
import com.artem.animationjikan.presentation.ui.screen.detail.animation.detail.detailContent
import com.artem.animationjikan.presentation.ui.screen.detail.animation.detail.detailDefaultGap
import com.artem.animationjikan.presentation.ui.screen.detail.animation.detail.detailImage
import com.artem.animationjikan.presentation.ui.screen.detail.animation.detail.detailScore
import com.artem.animationjikan.presentation.ui.screen.detail.animation.detail.detailTitle
import com.artem.animationjikan.presentation.ui.screen.detail.animation.detail.header
import com.artem.animationjikan.presentation.ui.screen.detail.animation.detail.renderTabContent
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
            if (event is UiEvent.ShowToast) {
                showToast(context = context, event.message)
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
            DetailTopBar(
                title = characterDetailViewModel.detailEntity.title,
                showTitle = showTitle,
                favoriteState = favoriteState,
                onBackPressed = { navController.popBackStack() },
                onFavoriteClick = { characterDetailViewModel.toggleFavorite() },
            )
        },
        content = { paddingValues ->
            when (characterDetailViewModel.state) {
                ViewModelState.Idle, ViewModelState.Loading ->
                    LoadingSpinner(modifier = Modifier.fillMaxSize())

                ViewModelState.Success -> {
                    CharacterDetailContent(
                        paddingValues = paddingValues,
                        detailUiState = DetailUiState(
                            scrollState = scrollState,
                            detailEntity = characterDetailViewModel.detailEntity,
                            selectedDestination = selectedDestination.value

                        ),
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


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun CharacterDetailContent(
    paddingValues: PaddingValues,
    detailUiState: DetailUiState,
    onTabClick: (DetailTabs) -> Unit,
    actorViewModel: ActorViewModel = hiltViewModel(),
    characterImageViewModel: CharacterImageViewModel = hiltViewModel(),
) {

    val tabTitles = listOf(R.string.actor, R.string.appearance_info, R.string.image)

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
            icon = R.drawable.ic_favorite_red_on,
            score = detailUiState.detailEntity.score.toInt().toString()
        )

        detailContent(synopsis = detailUiState.detailEntity.synopsis)

        header(
            onTabClick = onTabClick,
            tabTitles = tabTitles,
            selectedDestination = detailUiState.selectedDestination
        )

        detailDefaultGap()

        when (detailUiState.selectedDestination) {
            DetailTabs.FIRST -> {
                renderTabContent(
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
                renderTabContent(
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
                renderTabContent(
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
