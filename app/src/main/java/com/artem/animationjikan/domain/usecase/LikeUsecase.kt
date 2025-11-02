package com.artem.animationjikan.domain.usecase

import android.util.Log
import com.artem.animationjikan.domain.entities.LikeEntity
import com.artem.animationjikan.domain.repository.LikeRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class LikeUsecase @Inject constructor(
    private val likeRepository: LikeRepository
) {
    private val tag = "LikeUsecase"

    fun execute(mediaType: String): Flow<Result<List<LikeEntity>>> {
        return likeRepository.getAllLike(mediaType)
            .map { list -> Result.success(list) }
            .catch { error ->
                Log.e(tag, error.message.toString())
                emit(Result.failure(error))
            }
    }
}