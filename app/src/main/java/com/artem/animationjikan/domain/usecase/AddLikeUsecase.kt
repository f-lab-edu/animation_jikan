package com.artem.animationjikan.domain.usecase

import com.artem.animationjikan.data.dto.LikeData
import com.artem.animationjikan.domain.repository.LikeRepository
import javax.inject.Inject

class AddLikeUsecase @Inject constructor(
    private val likeRepository: LikeRepository
) {
    suspend fun execute(likeData: LikeData): Result<Unit> {
        return likeRepository.addLike(
            likeData = likeData,
        )
    }
}