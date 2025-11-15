package com.artem.animationjikan.data.repository

import com.artem.animationjikan.data.dto.ImagesDto
import com.artem.animationjikan.data.dto.MangaCharacterDTO
import com.artem.animationjikan.data.dto.MangaDTO
import com.artem.animationjikan.data.dto.ReviewDTO
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

    override suspend fun fetchMangaPictures(id: Int): List<ImagesDto> {
        return client.getMangaPictures(id = id).data
    }

    override suspend fun fetchMangaCharacters(id: Int): List<MangaCharacterDTO> {
        return client.getMangaCharacters(id = id).data
    }

    override suspend fun fetchMangaReviews(id: Int): List<ReviewDTO> {
        return client.getMangaReviews(id = id).data
    }
}