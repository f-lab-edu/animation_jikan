package com.artem.animationjikan.data.dto

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class RecommendationAnimationResponse(
    val pagination: Pagination,
    val data: List<RecommendationEntryResponse>,
)

@Serializable
data class RecommendationEntryResponse(
    val entry: List<RecommendationAnimationDTO>,
)

@Serializable
data class RecommendationAnimationDTO(
    @SerializedName("mal_id")
    val malId: Int = -1,
    @SerializedName("images")
    val images: ImagesDto,
)