package com.artem.animationjikan.data.repository

import com.artem.animationjikan.data.dto.CharacterDTO
import com.artem.animationjikan.data.service.remote.JikanApiClient
import com.artem.animationjikan.domain.repository.CharacterRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class CharacterRepositoryImpl @Inject constructor(
    private val client: JikanApiClient
) : CharacterRepository {
    override suspend fun fetchTopCharacters(): Flow<Result<List<CharacterDTO>>> = flow {
        val response = try {
            val result = client.getTopCharacters()
            Result.success(result.data)
        } catch (e: Exception) {
            Result.failure(e)
        }

        emit(response)
    }
}