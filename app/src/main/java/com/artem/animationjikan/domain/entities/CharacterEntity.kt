package com.artem.animationjikan.domain.entities

data class AnimeCharacterEntity(
    val malId: Int,
    val characterName: String,
    val imageUrl: String?,
    val actors: List<ActorEntity>,
    val role: String,
    val likeStatus: Boolean = false,
)

data class ActorEntity(
    val malId: Int,
    val imageUrl: String?,
    val name: String,
    val language: String,
)


data class CharacterDetailEntity(
    val malId: Int,
    val characterName: String,
    val imageUrl: String?,
    val likeCount: Int,
)