package com.artem.animationjikan.domain.usecase

import com.artem.animationjikan.data.mapper.toHomeCommonModel
import com.artem.animationjikan.domain.repository.AnimationRepository
import com.artem.animationjikan.presentation.model.CommonHomeContentModel
import com.artem.animationjikan.util.enums.FilterCategory
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetUpcomingUsecase @Inject constructor(
    private val animationRepository: AnimationRepository
) {
    suspend fun execute(): Flow<Result<List<CommonHomeContentModel>>> {
        val response = animationRepository.fetchUpcoming()
        return response.map { result ->
            result.map { dtoList ->
                dtoList.map {
                    it.toHomeCommonModel(type = FilterCategory.ANIMATION)
                }
            }
        }
        /*.fold(
            onSuccess = { data ->
                val result = data.map {
                    it.toHomeCommonModel(FilterCategory.ANIMATION)
                }

                Result.success(result)
            },
            onFailure = { error ->
                Result.failure(error)
            }
        )*/
    }
}