package com.artem.animationjikan.data.dto

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable


@Serializable
data class UpcomingResponse(
    val pagination: Pagination,
    val data: List<UpcomingDTO>
)

@Serializable
data class UpcomingDTO(
    @SerializedName("mal_id")
    val malId: Int = -1,
    val url: String,
    val images: ImagesDto,
)
