package com.artem.animationjikan.data.dto

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable


@Serializable
data class MangaResponse(
    val pagination: Pagination,
    val data: List<MangaDTO>?
)

@Serializable
data class MangaDTO(
    @SerializedName("mal_id")
    val malId: Int = -1,
    val url: String?,
    @SerializedName("images")
    val images: ImagesDto?,
    val title: String?,
    val synopsis: String? = null,
)