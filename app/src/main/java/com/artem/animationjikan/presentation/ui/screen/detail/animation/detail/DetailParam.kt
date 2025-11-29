package com.artem.animationjikan.presentation.ui.screen.detail.animation.detail

import androidx.compose.foundation.lazy.LazyListState
import com.artem.animationjikan.domain.entities.DetailEntity
import com.artem.animationjikan.domain.entities.HomeCommonEntity
import com.artem.animationjikan.util.enums.DetailTabs

data class DetailUiState(
    val detailEntity: DetailEntity,
    val selectedDestination: DetailTabs,
    val scrollState: LazyListState
)

data class DetailActions(
    val onTabClick: (DetailTabs) -> Unit,
    val onCharacterClick: (HomeCommonEntity) -> Unit
)