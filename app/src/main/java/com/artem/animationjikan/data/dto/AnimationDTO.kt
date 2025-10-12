package com.artem.animationjikan.data.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AnimationResponse(
    val pagination : PageNation,
    val entry : List<AnimationEntryDto>

)

@Serializable
data class PageNation(
    @SerialName("last_visible_page")
    val lastVisiblePage : Int,
    @SerialName("has_next_page")
    val hasNextPage : Boolean,
)

@Serializable
data class AnimationEntryDto(
    @SerialName("mal_id")
    val malId : Int,
    val title: String,
    val images : ImagesDto
)

@Serializable
data class ImagesDto(
    val jpg : JpgImageDto
)

@Serializable
data class JpgImageDto(
    @SerialName("image_url")
    val imageUrl : String
)