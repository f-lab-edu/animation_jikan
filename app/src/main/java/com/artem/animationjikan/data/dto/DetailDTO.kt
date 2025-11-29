package com.artem.animationjikan.data.dto

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable


interface DetailDTO {
    val malId: Int
    val images: ImagesDTO
    val title: String
    val synopsis: String
}

data class AnimationDetailResponseDTO(
    val data: AnimationDetailDTO,
)

data class AnimationDetailDTO(
    @SerializedName("mal_id")
    override val malId: Int = -1,
    @SerializedName("images")
    override val images: ImagesDTO,
    override val title: String,
    val score: Double,
    override val synopsis: String,
) : DetailDTO


@Serializable
data class MangaDetailResponseDTO(
    val data: MangaDetailDTO,
)

data class MangaDetailDTO(
    @SerializedName("mal_id")
    override val malId: Int,
    override val images: ImagesDTO,
    override val title: String,
    val score: Double,
    override val synopsis: String,
) : DetailDTO


@Serializable
data class CharacterDetailResponseDTO(
    val data: CharacterDetailDTO
)

data class CharacterDetailDTO(
    @SerializedName("mal_id")
    override val malId: Int,

    override val images: ImagesDTO,

    @SerializedName("name")
    override val title: String,

    val favorites: Int,

    @SerializedName("about")
    override val synopsis: String,

) : DetailDTO