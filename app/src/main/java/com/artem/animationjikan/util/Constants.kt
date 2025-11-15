package com.artem.animationjikan.util

import com.artem.animationjikan.R
import com.artem.animationjikan.util.enums.FilterCategory

object Route {
    const val HOME = "home"
    const val LIKE = "like"
    const val SEARCH = "search"
}

val CATEGORIES_LIST = listOf(
    R.string.animation_en,
    R.string.manga_en,
    R.string.character_en,
    R.string.voice_actor_en,
)

val FILTER_OPTION: List<FilterCategory> = listOf(
    FilterCategory.ALL,
    FilterCategory.ANIMATION,
    FilterCategory.MANGA,
    FilterCategory.VOICE_ACTOR,
    FilterCategory.CHARACTER
)

const val NO_ERROR_MESSAGE = "empty Error message"
