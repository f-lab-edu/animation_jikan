package com.artem.animationjikan.domain.usecase

import android.util.Log
import com.artem.animationjikan.data.mapper.toHomeCommonModel
import com.artem.animationjikan.domain.repository.AnimationRepository
import com.artem.animationjikan.presentation.model.CommonHomeContentModel
import com.artem.animationjikan.util.enums.FilterCategory
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetRecommendAnimationUsecase @Inject constructor(
    private val animationRepository: AnimationRepository
) {
    suspend fun execute(): Flow<Result<List<CommonHomeContentModel>>> {
        Log.d("GetRecommendAnimationUsecase", "execute")
        val response = animationRepository.fetchRecommendationAnimations()
        return response.map { result ->
            Log.d("GetRecommendAnimationUsecase", "execute result")
            result.map { dtoList ->
                val maxItemCount = 5

                dtoList.map { it.toHomeCommonModel(FilterCategory.ANIMATION) }
                    .shuffled()
                    .take(maxItemCount)
            }
        }
    }
}