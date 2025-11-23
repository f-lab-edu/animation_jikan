package com.artem.animationjikan.domain.repository

import com.artem.animationjikan.data.dto.VoiceActorDTO
import kotlinx.coroutines.flow.Flow

interface VoiceActorRepository {
    suspend fun searchAnime(query: String?): Flow<Result<List<VoiceActorDTO>>>
}