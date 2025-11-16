package com.artem.animationjikan.data.dto

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class AnimationResponse(
    val pagination: Pagination,
    val data: List<AnimeDTO>,
)

@Serializable
data class AnimeDTO(
    @SerializedName("mal_id")
    val malId: Int = -1,
    @SerializedName("images")
    val images: ImagesDTO,
    val title: String,
)