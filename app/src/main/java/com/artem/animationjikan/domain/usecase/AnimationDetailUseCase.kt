package com.artem.animationjikan.domain.usecase

import com.artem.animationjikan.data.mapper.toAnimationDetailEntity
import com.artem.animationjikan.domain.entities.AnimationDetailEntity
import com.artem.animationjikan.domain.repository.AnimationRepository
import javax.inject.Inject

class AnimationDetailUseCase @Inject constructor(
    private val animationRepository: AnimationRepository
) {
    suspend fun execute(id: Int): Result<AnimationDetailEntity> {
        return try {
            val result = animationRepository.fetchAnimeFullById(id = id).toAnimationDetailEntity()
            Result.success(result)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}