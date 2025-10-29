package com.artem.animationjikan.domain.repository

import com.artem.animationjikan.data.dto.LikeData
import kotlinx.coroutines.flow.Flow

interface LikeRepository {
    fun getAllLikeOfType(mediaType: String): Flow<Result<List<LikeData>>>

    suspend fun addLike(likeData: LikeData) : Result<Unit>

    suspend fun removeLike(mediaId: Int) : Result<Unit>

    fun getLikeStatus(mediaId: Int): Flow<Result<Boolean>>

}