package com.artem.animationjikan.domain.repository

import com.artem.animationjikan.data.dto.MangaDTO
import kotlinx.coroutines.flow.Flow

interface MangaRepository {
    suspend fun fetchTopManga(): Flow<List<MangaDTO>>
}