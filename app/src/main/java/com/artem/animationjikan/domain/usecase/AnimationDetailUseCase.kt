package com.artem.animationjikan.domain.usecase

import com.artem.animationjikan.data.mapper.toAnimationDetailEntity
import com.artem.animationjikan.domain.entities.DetailEntity
import com.artem.animationjikan.domain.repository.AnimationRepository
import javax.inject.Inject

class AnimationDetailUseCase @Inject constructor(
    private val animationRepository: AnimationRepository,
) {
    suspend fun getAnimationDetailInfo(id: Int): Result<DetailEntity> {
        return runCatching {
            animationRepository.fetchAnimeFullById(id = id).toAnimationDetailEntity()
        }
    }
}