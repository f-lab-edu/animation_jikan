package com.artem.animationjikan.data.repository

import androidx.compose.ui.res.stringResource
import com.artem.animationjikan.R
import com.artem.animationjikan.data.service.local.LikeDao
import com.artem.animationjikan.data.dto.LikeData
import com.artem.animationjikan.domain.entities.LikeEntity
import com.artem.animationjikan.domain.entities.toLikeData
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
        dao.getAllLikeOfType(type = mediaType)
            .map {
                Result.success(it.map { likeData ->
                    LikeEntity(
                        mediaId = likeData.mediaId,
                        imageUrl = likeData.imageUrl,
                        mediaType = likeData.mediaType,
                        isLiked = true
                    )
                })
            }.catch {
                emit(Result.failure(it))
            }.flowOn(Dispatchers.IO)

    override fun getAllLike(): Flow<Result<List<LikeEntity>>> =
        dao.getAllLike()
            .map {
                Result.success(it.map { likeData ->
                    LikeEntity(
                        mediaId = likeData.mediaId,
                        imageUrl = likeData.imageUrl,
                        mediaType = likeData.mediaType,
                        isLiked = true
                    )
                })
            }.catch {
                emit(Result.failure(it))
            }.flowOn(Dispatchers.IO)


    override suspend fun addLike(likeEntity: LikeEntity): Result<Unit> =
        try {
            dao.insert(likeEntity.toLikeData())
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }


    override suspend fun removeLike(mediaId: Int) = try {
        dao.delete(mediaId)
        Result.success(Unit)
    } catch (e: Exception) {
        Result.failure(e)
    }


    override fun getLikeStatus(mediaId: Int): Flow<Result<Boolean>> =
        dao.isLike(mediaId = mediaId).map {
            Result.success(it)
        }.catch {
            Result.failure<Exception>(it)
        }.flowOn(Dispatchers.IO)

}