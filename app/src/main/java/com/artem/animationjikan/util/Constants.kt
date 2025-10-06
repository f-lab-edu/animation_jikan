package com.artem.animationjikan.util

import androidx.compose.ui.res.stringResource
import com.artem.animationjikan.R
import com.artem.animationjikan.data.model.LikeModel
import com.artem.animationjikan.util.enums.FilterCategory

object Route {
    const val HOME = "home"
    const val LIKE = "like"
    const val SEARCH = "search"
}

const val SAMPLE_IMG_URL = "https://cdn.myanimelist.net/images/anime/1015/138006.jpg"

const val RECOMMEND_PAGE_COUNT = 5

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

val likeList = listOf(
    LikeModel(
        idx = 1,
        title = "어떤 만화1",
        isFavorite = true,
        imageUrl = "https://cdn.myanimelist.net/images/anime/3/72078.jpg"
    ),
    LikeModel(
        idx = 2,
        title = "어떤 만화2",
        isFavorite = true,
        imageUrl = "https://cdn.myanimelist.net/images/anime/1517/100633.jpg",
    ),
    LikeModel(
        idx = 2,
        title = "어떤 만화3",
        isFavorite = true,
        imageUrl = "https://cdn.myanimelist.net/images/anime/1245/116760.jpg"
    ),
    LikeModel(
        idx = 2,
        title = "어떤 만화4",
        isFavorite = true,
        imageUrl = "https://cdn.myanimelist.net/images/anime/1455/146229.jpg"
    ),
    LikeModel(
        idx = 2,
        title = "어떤 만화5",
        isFavorite = true,
        imageUrl = "https://cdn.myanimelist.net/images/anime/1337/99013.jpg"
    ),
    LikeModel(
        idx = 2,
        title = "어떤 만화6",
        isFavorite = true,
        imageUrl = "https://cdn.myanimelist.net/images/anime/1763/150638.jpg"
    ),
    LikeModel(
        idx = 2,
        title = "어떤 만화7",
        isFavorite = true,
        imageUrl = "https://cdn.myanimelist.net/images/anime/4/50361.jpg"
    ),
    LikeModel(
        idx = 2,
        title = "어떤 만화8",
        isFavorite = true,
        imageUrl = "https://cdn.myanimelist.net/images/anime/1908/135431.jpg"
    ),
    LikeModel(
        idx = 2,
        title = "어떤 만화9",
        isFavorite = true,
        imageUrl = "https://cdn.myanimelist.net/images/anime/1160/122627.jpg"
    ),
    LikeModel(
        idx = 2,
        title = "어떤 만화10",
        isFavorite = true,
        imageUrl = "https://cdn.myanimelist.net/images/anime/1085/114792.jpg"
    ),
)