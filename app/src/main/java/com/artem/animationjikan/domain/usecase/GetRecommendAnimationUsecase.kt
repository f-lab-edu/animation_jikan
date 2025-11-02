package com.artem.animationjikan.domain.usecase

import android.util.Log
import com.artem.animationjikan.data.mapper.toHomeCommonEntity
import com.artem.animationjikan.domain.entities.HomeCommonEntity
import com.artem.animationjikan.domain.repository.AnimationRepository
import com.artem.animationjikan.util.enums.FilterCategory
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetRecommendAnimationUsecase @Inject constructor(
    private val animationRepository: AnimationRepository
) {
    suspend fun execute(): Flow<Result<List<HomeCommonEntity>>> {
        val response = animationRepository.fetchRecommendationAnimations()
        return response.map { result ->
            result.map { dtoList ->
                val maxItemCount = 5
                dtoList.map { it.toHomeCommonEntity(FilterCategory.ANIMATION) }
                    .shuffled()
                    .take(maxItemCount)
            }
        }
    }
}