package com.artem.animationjikan.data.dto

data class MangaCharacterResponseDTO(
    val pagination: Pagination,
    val data: List<MangaCharacterDTO>,
)

data class MangaCharacterDTO(
    val character: List<CharacterDTO>,
)