package com.artem.animationjikan.presentation.ui.tab.home


import android.widget.Toast
import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.artem.animationjikan.R
import com.artem.animationjikan.domain.entities.HomeCommonEntity
import com.artem.animationjikan.presentation.ui.LocalNavScreenController
import com.artem.animationjikan.presentation.ui.components.HeightGap
import com.artem.animationjikan.presentation.ui.components.SearchView
import com.artem.animationjikan.presentation.ui.tab.home.components.ContentSectionRow
import com.artem.animationjikan.presentation.ui.tab.home.components.RecommendPager
import com.artem.animationjikan.presentation.ui.theme.AnimationJikanTheme
import com.artem.animationjikan.util.enums.FilterType
import com.artem.animationjikan.util.enums.ViewModelState
import com.artem.animationjikan.util.event.UiEvent
import com.artem.animationjikan.util.router.NavRoutes
import com.google.gson.Gson
import java.util.Base64

@Composable
fun HomeTab(
    modifier: Modifier = Modifier,
    viewModel: HomeTabViewModel = hiltViewModel()
) {
    val context = LocalContext.current
    val scrollState = rememberScrollState()
    val recommendAnimationList = viewModel.recommendationAnimationList.collectAsStateWithLifecycle()
    val navController = LocalNavScreenController.current
    val recentItems by viewModel.recentItemList.collectAsStateWithLifecycle()

    val navigationClick: (HomeCommonEntity) -> Unit = { entity ->
        val jsonString = Gson().toJson(entity)
        val encodedString = Base64.getUrlEncoder()

        val router = when(entity.type) {
            FilterType.ANIMATION -> NavRoutes.AnimationDetail.router
            FilterType.MANGA -> NavRoutes.MangaDetail.router
            FilterType.VOICE_ACTOR -> NavRoutes.VoiceActorDetail.router
            FilterType.CHARACTER -> NavRoutes.CharacterDetail.router
        }

        navController.navigate(
            "$router/" + encodedString.encodeToString(
                jsonString.toByteArray(
                    Charsets.UTF_8
                )
            )
        )
    }

    LaunchedEffect(key1 = Unit) {
        viewModel.eventFlow.collect { event ->
            when (event) {
                is UiEvent.ShowToast -> {
                    Toast.makeText(context, event.message, Toast.LENGTH_SHORT).show()
                }

                else -> Unit
            }
        }
    }

    Box(
        modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
            .padding(horizontal = 10.dp)
    ) {
        Column {
            HeightGap(6)

            SearchView(onClick = {
                navController.navigate(NavRoutes.Search.router)
            })

            //HeightGap(6)

            HeightGap(16)

            RecommendPager(
                recommendationAnimations = recommendAnimationList.value,
                isLoading = viewModel.state != ViewModelState.Success && viewModel.recommendationAnimationList.collectAsState().value.isEmpty()
            )

            HeightGap(25)

            if(recentItems.isNotEmpty()) {
                HomeContentSection(
                    titleRes = R.string.section_recently_viewed,
                    listState = viewModel.recentItemList.collectAsStateWithLifecycle(),
                    onItemLikeClick = { viewModel.toggleLike(entity = it) },
                    onItemClick = navigationClick,
                    viewModel = viewModel
                )
            }

            HomeContentSection(
                titleRes = R.string.section_upcoming_anime,
                listState = viewModel.upcomingList.collectAsStateWithLifecycle(),
                onItemLikeClick = { viewModel.toggleLike(entity = it) },
                onItemClick = navigationClick,
                viewModel = viewModel
            )

            HomeContentSection(
                titleRes = R.string.section_top_anime,
                listState = viewModel.topAnimationList.collectAsStateWithLifecycle(),
                onItemLikeClick = { viewModel.toggleLike(entity = it) },
                onItemClick = navigationClick,
                viewModel = viewModel
            )

            HomeContentSection(
                titleRes = R.string.section_top_manga,
                listState = viewModel.topMangaList.collectAsStateWithLifecycle(),
                onItemLikeClick = { viewModel.toggleLike(entity = it) },
                onItemClick = { _ -> },
                viewModel = viewModel
            )

            HomeContentSection(
                titleRes = R.string.section_top_character,
                listState = viewModel.topCharacterList.collectAsStateWithLifecycle(),
                onItemLikeClick = { viewModel.toggleLike(entity = it) },
                onItemClick = navigationClick,
                viewModel = viewModel
            )

        }
    }

}


@Composable
private fun HomeContentSection(
    @StringRes titleRes: Int, // 섹션 제목만 다름
    listState: State<List<HomeCommonEntity>>,
    onItemClick: (HomeCommonEntity) -> Unit,
    onItemLikeClick: (HomeCommonEntity) -> Unit,
    viewModel: HomeTabViewModel
) {
    val listValue = listState.value
    val isLoading = viewModel.state != ViewModelState.Success && listValue.isEmpty()

    ContentSectionRow(
        title = titleRes,
        list = listValue,
        isLoadingState = isLoading,
        onItemClick = onItemClick,
        onItemLikeClick = onItemLikeClick
    )
    HeightGap(16)
}


@Composable
@Preview
fun HomePreView() {
    AnimationJikanTheme {
        HomeTab(Modifier.fillMaxSize())
    }
}