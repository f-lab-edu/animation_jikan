package com.artem.animationjikan.data.repository

import com.artem.animationjikan.data.dto.VoiceActorDTO
import com.artem.animationjikan.data.service.remote.JikanApiClient
import com.artem.animationjikan.domain.repository.VoiceActorRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class VoiceActorRepositoryImpl @Inject constructor(
    private val client: JikanApiClient
) : VoiceActorRepository {
    override suspend fun searchVoiceActors(query: String?): Flow<Result<List<VoiceActorDTO>>> =
        flow {
            val result = runCatching { client.searchVoiceActor(query = query).data }
            emit(result)
        }
}