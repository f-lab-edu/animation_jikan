package com.artem.animationjikan.data.mapper

import com.artem.animationjikan.data.dto.AnimeDto
import com.artem.animationjikan.data.dto.CharacterDTO
import com.artem.animationjikan.data.dto.MangaDTO
import com.artem.animationjikan.data.dto.RecommendationAnimationDTO
import com.artem.animationjikan.data.dto.UpcomingDTO
import com.artem.animationjikan.presentation.model.CommonHomeContentModel
import com.artem.animationjikan.util.enums.FilterCategory

fun RecommendationAnimationDTO.toHomeCommonModel(type: FilterCategory): CommonHomeContentModel {
    return CommonHomeContentModel(
        id = this.malId,
        type = type,
        imageUrl = this.images.jpg.imageUrl,
    )
}

fun AnimeDto.toHomeCommonModel(type: FilterCategory): CommonHomeContentModel {
    return CommonHomeContentModel(
        id = this.malId,
        type = type,
        imageUrl = this.images.jpg.imageUrl,
    )
}

fun MangaDTO.toHomeCommonModel(type: FilterCategory): CommonHomeContentModel {
    return CommonHomeContentModel(
        id = this.malId,
        type = type,
        imageUrl = this.images.jpg.imageUrl,
    )
}

fun UpcomingDTO.toHomeCommonModel(type: FilterCategory): CommonHomeContentModel {
    return CommonHomeContentModel(
        id = this.malId,
        type = type,
        imageUrl = this.images.jpg.imageUrl,
    )
}

fun CharacterDTO.toHomeCommonModel(type: FilterCategory): CommonHomeContentModel {
    return CommonHomeContentModel(
        id = this.malId,
        type = type,
        imageUrl = this.images.jpg.imageUrl,
    )
}