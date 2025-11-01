package com.artem.animationjikan.domain.repository

import com.artem.animationjikan.domain.entities.LikeEntity
import kotlinx.coroutines.flow.Flow

interface LikeRepository {
    fun getAllLike(type: String): Flow<List<LikeEntity>>

    suspend fun addLike(likeEntity: LikeEntity)

    suspend fun removeLike(mediaId: Int)

    fun getLikeStatus(mediaId: Int): Flow<Boolean>
}