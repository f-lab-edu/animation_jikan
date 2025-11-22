package com.artem.animationjikan.domain.usecase

import com.artem.animationjikan.data.mapper.toHomeCommonEntity
import com.artem.animationjikan.domain.entities.HomeCommonEntity
import com.artem.animationjikan.domain.repository.AnimationRepository
import com.artem.animationjikan.util.enums.FilterType
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetUpcomingUseCase @Inject constructor(
    private val animationRepository: AnimationRepository
) {
    suspend fun execute(): Flow<Result<List<HomeCommonEntity>>> {
        return animationRepository.fetchUpcoming()
            .map { list ->
                runCatching {
                    list.map { item ->
                        item.toHomeCommonEntity(
                            FilterType.ANIMATION
                        )
                    }
                }
            }
    }
}