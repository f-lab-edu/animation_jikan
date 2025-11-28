package com.artem.animationjikan.data.dto


data class CharacterVoidActorResponseDTO(
    val data: List<CharacterVoiceActorDTO>,
)

data class CharacterVoiceActorDTO(
    val language: String,
    val person: PersonDTO,
)