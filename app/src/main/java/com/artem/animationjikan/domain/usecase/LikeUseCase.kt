package com.artem.animationjikan.domain.usecase

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.artem.animationjikan.domain.entities.LikeEntity
import com.artem.animationjikan.domain.repository.LikeRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

class LikeUseCase @Inject constructor(
    private val likeRepository: LikeRepository
) {
    private val tag = "LikeUseCase"

    fun execute(mediaType: String? = null): Flow<List<LikeEntity>> {
        return likeRepository.getAllLike(mediaType)
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

    suspend fun getLikeStatus(mediaId: Int): Flow<Boolean> {
        return likeRepository.getLikeStatus(mediaId)
            .catch {
                //TODO
                //emit()
            }
    }
}