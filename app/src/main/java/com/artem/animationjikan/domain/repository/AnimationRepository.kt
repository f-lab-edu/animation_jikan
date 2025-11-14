package com.artem.animationjikan.domain.repository

import com.artem.animationjikan.data.dto.AnimeCharacterDTO
import com.artem.animationjikan.data.dto.AnimeDto
import com.artem.animationjikan.data.dto.NewsDTO
import com.artem.animationjikan.data.dto.RecommendationAnimationDTO
import com.artem.animationjikan.data.dto.ReviewDTO
import com.artem.animationjikan.data.dto.UpcomingDTO
import kotlinx.coroutines.flow.Flow

interface AnimationRepository {
    suspend fun fetchTopAnimation(): Flow<List<AnimeDto>>

    suspend fun fetchRecommendationAnimations(): Flow<List<RecommendationAnimationDTO>>

    suspend fun fetchUpcoming(): Flow<List<UpcomingDTO>>

    suspend fun fetchAnimeNews(id: Int): List<NewsDTO>

    suspend fun fetchAnimeReview(id: Int): List<ReviewDTO>

    suspend fun fetchAnimeCharacters(id: Int): List<AnimeCharacterDTO>
}