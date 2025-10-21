package com.artem.animationjikan.data.dto

import kotlinx.serialization.Serializable
import com.google.gson.annotations.SerializedName

@Serializable
data class AnimationResponse(
    val pagination: Pagination,
    val data: List<AnimeDto>,
)

@Serializable
data class AnimeDto(
    @SerializedName("mal_id")
    val malId: Int = -1,
    @SerializedName("images")
    val images: ImagesDto,
    val title: String,
)