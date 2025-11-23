package com.artem.animationjikan.data.dto

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class VoiceActorResponseDTO(
    val pagination: Pagination,
    val data: List<VoiceActorDTO>,
)

@Serializable
data class VoiceActorDTO(
    @SerializedName("mal_id")
    val malId: Int = -1,
    val url: String,
    val images: ImagesDTO,
    val name: String,
)