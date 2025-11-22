package com.artem.animationjikan.data.repository

import android.util.Log
import com.artem.animationjikan.data.dto.AnimationDetailDTO
import com.artem.animationjikan.data.dto.AnimationResponseDTO
import com.artem.animationjikan.data.dto.AnimeCharacterDTO
import com.artem.animationjikan.data.dto.AnimeDTO
import com.artem.animationjikan.data.dto.NewsDTO
import com.artem.animationjikan.data.dto.RecommendationAnimationDTO
import com.artem.animationjikan.data.dto.ReviewDTO
import com.artem.animationjikan.data.dto.UpcomingDTO
import com.artem.animationjikan.data.dto.UpcomingResponseDTO
import com.artem.animationjikan.data.service.remote.JikanApiClient
import com.artem.animationjikan.domain.repository.AnimationRepository
import com.artem.animationjikan.util.network.retryOnRateLimit
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class AnimationRepositoryImpl @Inject constructor(
    private val client: JikanApiClient
) : AnimationRepository {

    override suspend fun fetchRecommendationAnimations(): Flow<List<RecommendationAnimationDTO>> =
        flow {
            val result: List<RecommendationAnimationDTO> =
                client.fetchRecommendationAnimations().data.flatMap { it.entry }
            emit(result)
        }.retryOnRateLimit()

    override suspend fun fetchTopAnimation(): Flow<List<AnimeDTO>> = flow {
        val response: AnimationResponseDTO = client.getTopAnimation()
        val result = response.data
        emit(result)
    }.retryOnRateLimit()


    override suspend fun fetchUpcoming(): Flow<List<UpcomingDTO>> = flow {
        val response: UpcomingResponseDTO = client.getUpcoming()
        val result = response.data
        emit(result)
    }.retryOnRateLimit()

    override suspend fun fetchAnimeNews(id: Int): List<NewsDTO> {
        return client.getAnimeNews(id).data
    }

    override suspend fun fetchAnimeReview(id: Int): List<ReviewDTO> {
        return client.getAnimeReviews(id).data
    }

    override suspend fun fetchAnimeCharacters(id: Int): List<AnimeCharacterDTO> {
        return client.getAnimeCharacters(id = id).data
    }

    override suspend fun fetchAnimeFullById(id: Int): AnimationDetailDTO {
        return client.getAnimeFullById(id = id).data
    }

    override suspend fun searchAnime(query: String?): Flow<Result<List<AnimeDTO>>> =
        flow {
            val result = runCatching { client.searchAnime(query = query).data }
            emit(result)
        }


}