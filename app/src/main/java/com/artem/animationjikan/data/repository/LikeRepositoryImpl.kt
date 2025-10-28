package com.artem.animationjikan.data.repository

import com.artem.animationjikan.data.service.local.LikeDao
import com.artem.animationjikan.domain.entities.LikeEntity
import com.artem.animationjikan.domain.repository.LikeRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class LikeRepositoryImpl @Inject constructor(
    private val dao: LikeDao
) : LikeRepository {

    override fun getAllLikeOfType(mediaType: String): Flow<Result<List<LikeEntity>>> =
        dao.getAllLikeOfType(type = mediaType).map {
            Result.success(it)
        }.catch {
            Result.failure<Exception>(it)
        }.flowOn(Dispatchers.IO)

    override suspend fun addLike(likeEntity: LikeEntity) =
        dao.insert(entity = likeEntity)

    override suspend fun removeLike(mediaId: Int) =
        dao.delete(mediaId)

    override fun getLikeStatus(mediaId: Int): Flow<Result<Boolean>> =
        dao.isLike(mediaId = mediaId).map {
            Result.success(it)
        }.catch {
            Result.failure<Exception>(it)
        }.flowOn(Dispatchers.IO)

}