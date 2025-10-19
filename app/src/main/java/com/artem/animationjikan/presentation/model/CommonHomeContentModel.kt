package com.artem.animationjikan.presentation.model

import com.artem.animationjikan.util.enums.FilterCategory

data class CommonHomeContentModel(
    val id: Int,
    val type: FilterCategory,
    val imageUrl: String = "",
)
