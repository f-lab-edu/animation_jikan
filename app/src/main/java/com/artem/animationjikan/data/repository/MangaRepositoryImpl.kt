package com.artem.animationjikan.data.repository

import com.artem.animationjikan.data.dto.MangaDTO
import com.artem.animationjikan.data.service.remote.JikanApiClient
import com.artem.animationjikan.domain.repository.MangaRepository
import com.artem.animationjikan.util.network.retryOnRateLimit
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class MangaRepositoryImpl @Inject constructor(
    private val client: JikanApiClient
) : MangaRepository {
    override suspend fun fetchTopManga(): Flow<List<MangaDTO>> = flow {
        val result = client.getTopManga().data
        emit(result)
    }.retryOnRateLimit()
}