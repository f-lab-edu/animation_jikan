package com.artem.animationjikan.data.repository

import com.artem.animationjikan.data.dto.AnimationResponse
import com.artem.animationjikan.data.dto.AnimeCharacterDTO
import com.artem.animationjikan.data.dto.AnimeDto
import com.artem.animationjikan.data.dto.NewsDTO
import com.artem.animationjikan.data.dto.RecommendationAnimationDTO
import com.artem.animationjikan.data.dto.ReviewDTO
import com.artem.animationjikan.data.dto.UpcomingDTO
import com.artem.animationjikan.data.dto.UpcomingResponse
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

    override suspend fun fetchTopAnimation(): Flow<List<AnimeDto>> = flow {
        val response: AnimationResponse = client.getTopAnimation()
        val result = response.data
        emit(result)
    }.retryOnRateLimit()


    override suspend fun fetchUpcoming(): Flow<List<UpcomingDTO>> = flow {
        val response: UpcomingResponse = client.getUpcoming()
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

}