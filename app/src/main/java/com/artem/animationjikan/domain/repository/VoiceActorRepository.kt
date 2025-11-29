package com.artem.animationjikan.domain.repository

import com.artem.animationjikan.data.dto.CharacterVoiceActorDTO
import com.artem.animationjikan.data.dto.VoiceActorDTO
import kotlinx.coroutines.flow.Flow

interface VoiceActorRepository {
    suspend fun searchVoiceActors(query: String?): Flow<Result<List<VoiceActorDTO>>>

    suspend fun fetchCharacterVoiceActors(id: Int): Result<List<CharacterVoiceActorDTO>>

}