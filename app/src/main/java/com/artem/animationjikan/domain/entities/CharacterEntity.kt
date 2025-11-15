package com.artem.animationjikan.domain.entities

data class AnimeCharacterEntity(
    val malId: Int,
    val characterName: String,
    val imageUrl: String?,
    val actors: List<ActorEntity>,
    val role: String,
)

data class ActorEntity(
    val malId: Int,
    val imageUrl: String?,
    val name: String,
    val language: String,
)