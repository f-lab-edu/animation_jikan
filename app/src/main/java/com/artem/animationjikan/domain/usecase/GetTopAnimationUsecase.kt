package com.artem.animationjikan.domain.usecase

import com.artem.animationjikan.data.mapper.toHomeCommonEntity
import com.artem.animationjikan.domain.entities.HomeCommonEntity
import com.artem.animationjikan.domain.repository.AnimationRepository
import com.artem.animationjikan.util.enums.FilterCategory
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetTopAnimationUsecase @Inject constructor(
    private val animationRepository: AnimationRepository
) {
    suspend fun execute(): Flow<Result<List<HomeCommonEntity>>> {
        return animationRepository.fetchTopAnimation().map { list ->
            val result = list.map {
                it.toHomeCommonEntity(FilterCategory.ANIMATION)
            }

            Result.success(result)
        }.catch {
            emit(Result.failure(it))
        }
    }
}