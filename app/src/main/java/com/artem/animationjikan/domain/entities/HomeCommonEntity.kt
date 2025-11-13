package com.artem.animationjikan.domain.entities

import com.artem.animationjikan.util.enums.FilterCategory

data class HomeCommonEntity(
    val id: Int = -1,
    val type: FilterCategory,
    val likeStatus: Boolean = false,
    val imageUrl: String = "",
)