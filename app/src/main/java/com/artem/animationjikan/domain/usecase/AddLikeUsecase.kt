package com.artem.animationjikan.domain.usecase

import android.util.Log
import com.artem.animationjikan.domain.entities.LikeEntity
import com.artem.animationjikan.domain.repository.LikeRepository
import javax.inject.Inject

class AddLikeUsecase @Inject constructor(
    private val likeRepository: LikeRepository
) {
    private val tag = "AddLikeUsecase"

    suspend fun execute(likeEntity: LikeEntity): Result<Unit> {
        return try {
            likeRepository.addLike(likeEntity = likeEntity)
            Result.success(Unit)
        } catch (e: Exception) {
            Log.e(tag, e.message.toString())
            Result.failure(e)
        }
    }
}