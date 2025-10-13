package com.artem.animationjikan.presentation.ui.tab.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.SuggestionChip
import androidx.compose.material3.SuggestionChipDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.artem.animationjikan.R
import com.artem.animationjikan.data.dto.SampleContentSectionItem
import com.artem.animationjikan.data.repository.AnimationRepository
import com.artem.animationjikan.data.repository.AnimationRepositoryImpl
import com.artem.animationjikan.data.repository.CharacterRepository
import com.artem.animationjikan.data.repository.CharacterRepositoryImpl
import com.artem.animationjikan.data.repository.MangaRepository
import com.artem.animationjikan.data.repository.MangaRepositoryImpl
import com.artem.animationjikan.data.service.remote.JikanApiClient
import com.artem.animationjikan.data.service.remote.JikanApiService
import com.artem.animationjikan.data.service.remote.createJikanApiService
import com.artem.animationjikan.presentation.factory.HomeTabViewModelFactory
import com.artem.animationjikan.presentation.ui.tab.home.components.ContentSectionRow
import com.artem.animationjikan.presentation.ui.tab.home.components.RecommendPager
import com.artem.animationjikan.presentation.ui.theme.AnimationJikanTheme
import com.artem.animationjikan.presentation.viewmodel.HomeTabViewModel
import com.artem.animationjikan.util.CATEGORIES_LIST
import com.artem.animationjikan.util.RECOMMEND_PAGE_COUNT
import com.artem.animationjikan.util.SAMPLE_IMG_URL

fun createHomeTabViewModelFactory() : HomeTabViewModelFactory {
    val service : JikanApiService = createJikanApiService()
    val client = JikanApiClient(service)
    val animRepository : AnimationRepository = AnimationRepositoryImpl(client)
    val mangaRepository : MangaRepository = MangaRepositoryImpl(client)
    val characterRepository : CharacterRepository = CharacterRepositoryImpl(client)

    return HomeTabViewModelFactory(
        animationRepository = animRepository,
        mangaRepository = mangaRepository,
        characterRepository = characterRepository
    )
}

@Composable
fun HomeTab(
    modifier: Modifier = Modifier
) {
    val factory = createHomeTabViewModelFactory()

    val viewModel : HomeTabViewModel = viewModel(factory = factory)

    val chipItems = CATEGORIES_LIST

    val scrollState = rememberScrollState()

    Box(
        modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
            .padding(horizontal = 10.dp)
    ) {
        Column {
            Spacer(modifier = Modifier.height(6.dp))
            LazyRow(
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(chipItems) { item ->
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
            Spacer(modifier = Modifier.height(16.dp))
            RecommendPager(pageCount = RECOMMEND_PAGE_COUNT)

            Spacer(modifier = Modifier.height(25.dp))

            ContentSectionRow(
                R.string.section_recently_viewed,
                listOf(
                    SampleContentSectionItem(
                        idx = 1,
                        url = SAMPLE_IMG_URL
                    ),
                    SampleContentSectionItem(
                        idx = 2,
                        url = SAMPLE_IMG_URL
                    ),
                    SampleContentSectionItem(
                        idx = 3,
                        url = SAMPLE_IMG_URL
                    ),
                    SampleContentSectionItem(
                        idx = 4,
                        url = SAMPLE_IMG_URL
                    ),
                    SampleContentSectionItem(
                        idx = 5,
                        url = SAMPLE_IMG_URL
                    ),

                    ),
                onItemClick = { idx ->

                }
            )

            Spacer(modifier = Modifier.height(16.dp))

            ContentSectionRow(
                R.string.section_upcoming_anime,
                listOf(
                    SampleContentSectionItem(
                        idx = 1,
                        url = SAMPLE_IMG_URL
                    ),
                    SampleContentSectionItem(
                        idx = 2,
                        url = SAMPLE_IMG_URL
                    ),
                    SampleContentSectionItem(
                        idx = 3,
                        url = SAMPLE_IMG_URL
                    ),
                ),
                onItemClick = { idx ->

                }
            )


            Spacer(modifier = Modifier.height(16.dp))

            ContentSectionRow(
                R.string.section_top_anime,
                listOf(
                    SampleContentSectionItem(
                        idx = 1,
                        url = SAMPLE_IMG_URL
                    ),
                    SampleContentSectionItem(
                        idx = 2,
                        url = SAMPLE_IMG_URL
                    ),
                    SampleContentSectionItem(
                        idx = 3,
                        url = SAMPLE_IMG_URL
                    ),
                ),
                onItemClick = { idx ->

                }
            )

            Spacer(modifier = Modifier.height(16.dp))

            ContentSectionRow(
                R.string.section_top_manga,
                listOf(
                    SampleContentSectionItem(
                        idx = 1,
                        url = SAMPLE_IMG_URL
                    ),
                    SampleContentSectionItem(
                        idx = 1,
                        url = SAMPLE_IMG_URL
                    ),
                    SampleContentSectionItem(
                        idx = 1,
                        url = SAMPLE_IMG_URL
                    ),
                ),
                onItemClick = { idx ->

                }
            )


            Spacer(modifier = Modifier.height(16.dp))

            ContentSectionRow(
                R.string.section_top_character,
                listOf(
                    SampleContentSectionItem(
                        idx = 1,
                        url = SAMPLE_IMG_URL,
                    ),
                    SampleContentSectionItem(
                        idx = 1,
                        url = SAMPLE_IMG_URL
                    ),
                    SampleContentSectionItem(
                        idx = 1,
                        url = SAMPLE_IMG_URL
                    ),
                    SampleContentSectionItem(
                        idx = 1,
                        url = SAMPLE_IMG_URL
                    ),
                    SampleContentSectionItem(
                        idx = 1,
                        url = SAMPLE_IMG_URL
                    ),


                    ),
                onItemClick = { idx ->

                }
            )

            Spacer(modifier = Modifier.height(30.dp))


        }
    }
}


@Composable
@Preview
fun HomePreView() {
    AnimationJikanTheme {
        HomeTab(Modifier.fillMaxSize())
    }
}