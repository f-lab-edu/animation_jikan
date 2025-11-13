package com.artem.animationjikan.domain.usecase

import android.util.Log
import com.artem.animationjikan.domain.entities.LikeEntity
import com.artem.animationjikan.domain.repository.LikeRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class LikeUseCase @Inject constructor(
    private val likeRepository: LikeRepository
) {
    private val tag = "LikeUseCase"

    fun execute(mediaType: String? = null): Flow<List<LikeEntity>> {
        return likeRepository.getAllLike(mediaType)
            .map { list -> list }
    }

    suspend fun addLike(likeEntity: LikeEntity): Result<Unit> {
        return try {
            likeRepository.addLike(likeEntity = likeEntity)
            Result.success(Unit)
        } catch (e: Exception) {
            Log.e(tag, e.message.toString())
            Result.failure(e)
        }
    }

    suspend fun removeLike(mediaId: Int): Result<Unit> {
        return try {
            likeRepository.removeLike(mediaId = mediaId)
            Result.success(Unit)
        } catch (e: Exception) {
            Log.e(tag, e.message.toString())
            Result.failure(e)
        }
    }
}