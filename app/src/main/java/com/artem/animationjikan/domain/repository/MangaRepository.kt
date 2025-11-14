package com.artem.animationjikan.domain.repository

import com.artem.animationjikan.data.dto.CharacterDTO
import com.artem.animationjikan.data.dto.ImagesDto
import com.artem.animationjikan.data.dto.MangaDTO
import kotlinx.coroutines.flow.Flow

interface MangaRepository {
    suspend fun fetchTopManga(): Flow<List<MangaDTO>>

    suspend fun fetchMangaPictures(malId: Int): List<ImagesDto>

    suspend fun fetchMangaCharacters(malId: Int): List<CharacterDTO>

    suspend fun fetchMangaReviews(malId: Int): List<CharacterDTO>
    ///TODO return type 변경하기 현재 CharacterDTO


}