package com.artem.animationjikan.domain.repository

import kotlinx.coroutines.flow.Flow

interface LikeRepository {
    fun getAllLikeOfType(mediaType: String): Flow<List<Int>>

    suspend fun updateLikeStatus(mediaId: Int, isLikedL: Boolean)

    fun getLikeStatus(mediaId: Int): Flow<Boolean>
}