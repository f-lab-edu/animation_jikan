package com.artem.animationjikan.data.dto

import com.google.gson.annotations.SerializedName

data class AnimationDetailResponse(
    val data: AnimationDetailDTO,
)

data class AnimationDetailDTO(
    @SerializedName("mal_id")
    val malId: Int = -1,
    @SerializedName("images")
    val images: ImagesDTO,
    val title: String,
    val score: Double,
    val synopsis: String,
)