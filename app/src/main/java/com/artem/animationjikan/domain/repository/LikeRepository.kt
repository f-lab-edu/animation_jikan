package com.artem.animationjikan.domain.repository

import com.artem.animationjikan.domain.entities.LikeEntity
import kotlinx.coroutines.flow.Flow

interface LikeRepository {
    fun getAllLikeOfType(mediaType: String): Flow<Result<List<LikeEntity>>>

    suspend fun addLike(likeEntity: LikeEntity) : Result<Unit>

    suspend fun removeLike(mediaId: Int) : Result<Unit>

    fun getLikeStatus(mediaId: Int): Flow<Result<Boolean>>

}