package com.artem.animationjikan.data.dto

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class NewsResponse(
    val pagination: Pagination,
    val data: List<NewsDTO>,
)

@Serializable
data class NewsDTO(
    @SerializedName("mal_id")
    val malId: Int = -1,
    val title: String,
    val date: String,
    @SerializedName("author_username")
    val authorUsername: String,
    val images: ImagesDto,
    val excerpt: String,
)