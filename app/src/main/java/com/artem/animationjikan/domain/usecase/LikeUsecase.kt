package com.artem.animationjikan.domain.usecase

import com.artem.animationjikan.domain.entities.LikeEntity
import com.artem.animationjikan.domain.repository.LikeRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LikeUsecase @Inject constructor(
    private val likeRepository: LikeRepository
) {
    fun execute(mediaType: String): Flow<Result<List<LikeEntity>>> {
        if (mediaType == "ALL") {
            return likeRepository.getAllLike()
        }
        return likeRepository.getAllLikeOfType(mediaType = mediaType)
    }
}