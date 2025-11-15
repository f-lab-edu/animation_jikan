package com.artem.animationjikan.presentation.ui.tab.home

import android.widget.Toast
import androidx.annotation.StringRes
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.SuggestionChip
import androidx.compose.material3.SuggestionChipDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.artem.animationjikan.R
import com.artem.animationjikan.domain.entities.HomeCommonEntity
import com.artem.animationjikan.presentation.ui.LocalNavScreenController
import com.artem.animationjikan.presentation.ui.components.HeightGap
import com.artem.animationjikan.presentation.ui.components.WidthGap
import com.artem.animationjikan.presentation.ui.tab.home.components.ContentSectionRow
import com.artem.animationjikan.presentation.ui.tab.home.components.RecommendPager
import com.artem.animationjikan.presentation.ui.theme.AnimationJikanTheme
import com.artem.animationjikan.util.CATEGORIES_LIST
import com.artem.animationjikan.util.event.UiEvent
import com.artem.animationjikan.util.router.NavRoutes

@Composable
fun HomeTab(
    modifier: Modifier = Modifier,
    viewModel: HomeTabViewModel = hiltViewModel()
) {
    val context = LocalContext.current
    val scrollState = rememberScrollState()
    val recommendAnimationList = viewModel.recommendationAnimationList.collectAsStateWithLifecycle()
    val navController = LocalNavScreenController.current

    val animeNavigationClick: (HomeCommonEntity) -> Unit = { entity ->
        navController.navigate(NavRoutes.AnimationDetail.router + "/${entity.id}")
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

            SearchView()

            HeightGap(6)

            ChipSection()

            HeightGap(16)

            RecommendPager(
                recommendationAnimations = recommendAnimationList.value,
                isLoading = viewModel.state != ViewModelState.Success && viewModel.recommendationAnimationList.collectAsState().value.isEmpty()
            )

            HeightGap(25)

            HomeContentSection(
                titleRes = R.string.section_recently_viewed,
                listState = viewModel.topAnimationList.collectAsStateWithLifecycle(),
                onItemLikeClick = { viewModel.toggleLike(entity = it) },
                onItemClick = animeNavigationClick,
                viewModel = viewModel
            )

            HomeContentSection(
                titleRes = R.string.section_upcoming_anime,
                listState = viewModel.upcomingList.collectAsStateWithLifecycle(),
                onItemLikeClick = { viewModel.toggleLike(entity = it) },
                onItemClick = animeNavigationClick,
                viewModel = viewModel
            )

            HomeContentSection(
                titleRes = R.string.section_top_anime,
                listState = viewModel.topAnimationList.collectAsStateWithLifecycle(),
                onItemLikeClick = { viewModel.toggleLike(entity = it) },
                onItemClick = animeNavigationClick,
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
                onItemClick = { _ -> },
                viewModel = viewModel
            )

        }
    }

}

@Composable
fun SearchView() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(40.dp)
            .clip(shape = RoundedCornerShape(8.dp))
            .border(
                width = 1.dp,
                color = colorResource(R.color.grey4),
                shape = RoundedCornerShape(8.dp)
            )

    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .align(Alignment.Center)
                .padding(horizontal = 10.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                painter = painterResource(R.drawable.ic_search_off),
                tint = colorResource(R.color.grey4),
                modifier = Modifier.size(18.dp),
                contentDescription = stringResource(R.string.search)
            )
            WidthGap(8)
            Text(
                stringResource(R.string.search_default_txt),
                modifier = Modifier.weight(1f),
                fontSize = 11.sp,
                fontWeight = FontWeight(400),
                color = colorResource(R.color.grey4)
            )
        }

    }
}

@Composable
fun ChipSection() {
    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(CATEGORIES_LIST) { item ->
            SuggestionChip(
                onClick = {},
                label = {
                    Text(stringResource(item))
                },
                colors = SuggestionChipDefaults.suggestionChipColors(
                    containerColor = Color.Black,
                    labelColor = Color.White
                )
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

@Composable
@Preview
fun SearchViewPreView() {
    SearchView()
}
