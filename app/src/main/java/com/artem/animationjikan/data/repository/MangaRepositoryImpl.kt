package com.artem.animationjikan.data.repository

import com.artem.animationjikan.data.dto.MangaDTO
import com.artem.animationjikan.data.service.remote.JikanApiClient
import com.artem.animationjikan.domain.repository.MangaRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class MangaRepositoryImpl @Inject constructor(
    private val client: JikanApiClient
) : MangaRepository {
    override suspend fun fetchTopManga(): Flow<Result<List<MangaDTO>>> = flow {
        val response = try {
            val result = client.getTopManga().data
            Result.success(result)
        } catch (e: Exception) {
            Result.failure(e)
        }
        emit(response)
    }
}