package com.artem.animationjikan.domain.repository

import com.artem.animationjikan.data.dto.AnimeDto
import com.artem.animationjikan.data.dto.RecommendationAnimationDTO
import com.artem.animationjikan.data.dto.UpcomingDTO
import kotlinx.coroutines.flow.Flow

interface AnimationRepository {
    suspend fun fetchTopAnimation(): Flow<Result<List<AnimeDto>>>

    suspend fun fetchRecommendationAnimations(): Flow<Result<List<RecommendationAnimationDTO>>>

    suspend fun fetchUpcoming(): Flow<Result<List<UpcomingDTO>>>
}