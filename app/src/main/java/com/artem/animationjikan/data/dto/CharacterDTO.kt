package com.artem.animationjikan.data.dto

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class CharacterResponse(
    val pagination: Pagination,
    val data: List<CharacterDTO>?,
)

@Serializable
data class CharacterDTO(
    @SerializedName("mal_id")
    val malId: Int = -1,
    val url: String,
    val images: ImagesDto,
    val name: String,
)
