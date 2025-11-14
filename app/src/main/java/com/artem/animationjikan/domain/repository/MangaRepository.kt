package com.artem.animationjikan.domain.repository

import com.artem.animationjikan.data.dto.ImagesDto
import com.artem.animationjikan.data.dto.MangaCharacterDTO
import com.artem.animationjikan.data.dto.MangaDTO
import com.artem.animationjikan.data.dto.ReviewDTO
import kotlinx.coroutines.flow.Flow

interface MangaRepository {
    suspend fun fetchTopManga(): Flow<List<MangaDTO>>

    suspend fun fetchMangaPictures(id: Int): List<ImagesDto>

    suspend fun fetchMangaCharacters(id: Int): List<MangaCharacterDTO>

    suspend fun fetchMangaReviews(id: Int): List<ReviewDTO>

}