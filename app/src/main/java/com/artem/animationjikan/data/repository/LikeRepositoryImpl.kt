package com.artem.animationjikan.data.repository

import android.util.Log
import com.artem.animationjikan.data.service.local.LikeDao
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

    companion object {
        const val TAG : String = "LikeRepositoryImpl"
    }

    override fun getAllLike(type: String?): Flow<List<LikeEntity>> =
        dao.getLikesList(type)
            .map {
                it.map { likeData ->
                    LikeEntity(
                        mediaId = likeData.mediaId,
                        imageUrl = likeData.imageUrl,
                        mediaType = likeData.mediaType,
                    )
                }
            }.catch { e ->
                Log.e(TAG, e.message.toString())
                throw e
            }.flowOn(Dispatchers.IO)

    override suspend fun addLike(likeEntity: LikeEntity) =
        try {
            dao.insert(likeEntity.toLikeData())
        } catch (e: Exception) {
            Log.e(TAG, e.message.toString())
            throw e
        }

    override suspend fun removeLike(mediaId: Int) = try {
        dao.delete(mediaId)
    } catch (e: Exception) {
        Log.e(TAG, e.message.toString())
        throw e
    }

    override fun getLikeStatus(mediaId: Int): Flow<Boolean> =
        dao.isLike(mediaId = mediaId).map {
            it
        }.catch { e ->
            Log.e(TAG, e.message.toString())
            throw e
        }.flowOn(Dispatchers.IO)

}