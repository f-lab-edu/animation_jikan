package com.artem.animationjikan.data.repository

import com.artem.animationjikan.data.service.local.LikeDao
import com.artem.animationjikan.domain.repository.LikeRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LikeRepositoryImpl @Inject constructor(
    private val dao: LikeDao
) : LikeRepository {

    override fun getAllLikeOfType(mediaType : String): Flow<List<Int>> {
        return dao.getAllLikeOfType(mediaType)
    }

    override suspend fun updateLikeStatus(mediaId: Int, isLikedL: Boolean) {

    }

    override fun getLikeStatus(mediaId: Int): Flow<Boolean> {
        TODO("Not yet implemented")
    }

}