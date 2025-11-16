package com.artem.animationjikan.domain.usecase

import android.util.Log
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
            Log.e("AnimationDetailUseCase","result $result")
            Result.success(result)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}