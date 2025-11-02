package com.artem.animationjikan.domain.usecase

import android.util.Log
import com.artem.animationjikan.domain.repository.LikeRepository
import javax.inject.Inject

class RemoveLikeUsecase @Inject constructor(
    private val likeRepository: LikeRepository
) {
    private val tag = "RemoveLikeUsecase"

    suspend fun execute(mediaId: Int): Result<Unit> {
        try {
            likeRepository.removeLike(mediaId = mediaId)
            return Result.success(Unit)
        } catch (e: Exception) {
            Log.e(tag, e.message.toString())
            return Result.failure(e)
        }
    }
}