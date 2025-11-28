package com.artem.animationjikan.domain.entities

data class CharacterVoiceActorEntity(
    val malId: Int,
    val language: String,
    val imageUrl: String?,
    val name: String,
    val likeStatus: Boolean = false,
)