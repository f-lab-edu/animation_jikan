package com.artem.animationjikan.presentation.ui.screen.search

import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.artem.animationjikan.R
import com.artem.animationjikan.domain.entities.HomeCommonEntity
import com.artem.animationjikan.presentation.ui.LocalNavScreenController
import com.artem.animationjikan.presentation.ui.components.ErrorWidget
import com.artem.animationjikan.presentation.ui.components.HeightGap
import com.artem.animationjikan.presentation.ui.components.JikanNetworkCardImage
import com.artem.animationjikan.presentation.ui.components.LoadingSpinner
import com.artem.animationjikan.presentation.ui.components.SearchView
import com.artem.animationjikan.presentation.ui.components.TypeChip
import com.artem.animationjikan.util.enums.ViewModelState
import com.artem.animationjikan.util.router.NavRoutes
import com.google.gson.Gson
import java.util.Base64

@Composable
fun SearchScreen(
    searchViewModel: SearchViewModel = hiltViewModel()
) {

    val searchItemList by searchViewModel.contentList.collectAsStateWithLifecycle()

    val navController = LocalNavScreenController.current

    val resultState by searchViewModel.searchResult.collectAsStateWithLifecycle()

    val status by searchViewModel.status.collectAsStateWithLifecycle()


    val animeNavigationClick: (HomeCommonEntity) -> Unit = { entity ->
        val jsonString = Gson().toJson(entity)
        val encodedString = Base64.getUrlEncoder()

        navController.navigate(
            NavRoutes.AnimationDetail.router + "/" + encodedString.encodeToString(
                jsonString.toByteArray(
                    Charsets.UTF_8
                )
            )
        )
    }


    Scaffold(
        containerColor = colorResource(R.color.black),
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues = paddingValues)
        ) {
            Column(
                modifier = Modifier.padding(all = 10.dp),
            ) {
                SearchView(
                    isShowTextField = true,
                    onValueChange = {
                        searchViewModel.onQueryChange(it)
                    }
                )

                HeightGap(height = 5)

                TypeChip { type ->
                    searchViewModel.updateFilter(type = type)
                }

                HeightGap(height = 16)

                Box(modifier = Modifier.fillMaxSize()) {
                    when (status) {
                        ViewModelState.Idle, ViewModelState.Loading -> {
                            LoadingSpinner(modifier = Modifier.fillMaxSize())
                        }

                        ViewModelState.Success -> {
                            val list = resultState.getOrNull()
                            if (!list.isNullOrEmpty()) {
                                SearchGrid(
                                    searchList = searchItemList,
                                    onItemClick = { animeNavigationClick(it) }
                                )
                            } else {
                                ErrorWidget(
                                    messageStringResId = R.string.fail_load_data,
                                )
                            }
                        }

                        ViewModelState.Error -> {
                            ErrorWidget(
                                messageStringResId = R.string.fail_load_data,
                            )
                        }
                    }
                }

            }
        }
    }
}

@Composable
fun SearchGrid(
    searchList: List<HomeCommonEntity>,
    onItemClick: (HomeCommonEntity) -> Unit
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(3),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        contentPadding = PaddingValues(bottom = 25.dp),
        modifier = Modifier.fillMaxSize(),
    ) {
        items(
            items = searchList,
            key = { item -> item.id }
        ) { item ->
            SearchGridItem(
                model = item,
                modifier = Modifier.animateItem(
                    fadeInSpec = tween(durationMillis = 250),
                    fadeOutSpec = tween(durationMillis = 100),
                    placementSpec = spring(stiffness = Spring.StiffnessLow)
                ),
                onItemClick = { onItemClick(item) },
            )
        }
    }
}

@Composable
fun SearchGridItem(
    model: HomeCommonEntity,
    onItemClick: (HomeCommonEntity) -> Unit,
    modifier: Modifier
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        JikanNetworkCardImage(
            modifier = Modifier
                .aspectRatio(9f / 13f)
                .clip(
                    RoundedCornerShape(4.dp)
                ),
            imageUrl = model.imageUrl,
            result = model,
            onClick = { onItemClick(model) },
        )
    }
}