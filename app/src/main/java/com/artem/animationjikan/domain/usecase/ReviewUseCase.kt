package com.artem.animationjikan.domain.usecase

import com.artem.animationjikan.data.mapper.toReviewEntity
import com.artem.animationjikan.domain.entities.ReviewEntity
import com.artem.animationjikan.domain.repository.AnimationRepository
import javax.inject.Inject

class ReviewUseCase @Inject constructor(
    private val animationRepository: AnimationRepository
) {
    suspend fun execute(malId: Int): Result<List<ReviewEntity>> {
        return try {
            val result = animationRepository.fetchAnimeReview(id = malId)
            Result.success(result.map { it.toReviewEntity() })
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}