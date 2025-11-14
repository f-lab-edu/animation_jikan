package com.artem.animationjikan.data.dto

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class CharacterResponse(
    val pagination: Pagination,
    val data: List<CharacterDTO>,
)

@Serializable
data class CharacterDTO(
    @SerializedName("mal_id")
    val malId: Int = -1,
    val url: String,
    val images: ImagesDto,
    val name: String,
)


@Serializable
data class AnimeCharacterResponse(
    val data: List<AnimeCharacterDTO>
)

@Serializable
data class AnimeCharacterDTO(
    val character: AnimeCharacterDetailDTO,
    @SerializedName("voice_actors")
    val actors: List<ActorDTO>,
    val role: String,
)

@Serializable
data class AnimeCharacterDetailDTO(
    @SerializedName("mal_id")
    val malId: Int = -1,
    val url: String,
    val images: ImagesDto,
    val name: String,
)

@Serializable
data class ActorDTO(
    val person: PersonDTO,
    val language: String,
)

@Serializable
data class PersonDTO(
    @SerializedName("mal_id")
    val malId: Int,
    val images: ImagesDto,
    val name: String,
)