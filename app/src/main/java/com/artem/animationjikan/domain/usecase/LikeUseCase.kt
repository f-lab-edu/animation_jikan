package com.artem.animationjikan.domain.usecase

import android.util.Log
import com.artem.animationjikan.domain.entities.LikeEntity
import com.artem.animationjikan.domain.repository.LikeRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LikeUseCase @Inject constructor(
    private val likeRepository: LikeRepository
) {
    private val tag = "LikeUseCase"

    fun execute(mediaType: String? = null): Flow<List<LikeEntity>> {
        return likeRepository.getAllLike(mediaType)
    }

    suspend fun addLike(likeEntity: LikeEntity): Result<Unit> {
        return runCatching {
            likeRepository.addLike(likeEntity = likeEntity)
        }
    }

    suspend fun removeLike(mediaId: Int): Result<Unit> {
        return runCatching {
            likeRepository.removeLike(mediaId = mediaId)
        }
    }

    fun getLikeStatus(mediaId: Int): Flow<Boolean> {
        return likeRepository.getLikeStatus(mediaId)
    }
}