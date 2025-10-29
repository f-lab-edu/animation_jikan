package com.artem.animationjikan.data.repository

import android.util.Log
import com.artem.animationjikan.data.service.local.LikeDao
import com.artem.animationjikan.domain.entities.LikeData
import com.artem.animationjikan.domain.repository.LikeRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import javax.inject.Inject

class LikeRepositoryImpl @Inject constructor(
    private val dao: LikeDao
) : LikeRepository {

    override fun getAllLikeOfType(mediaType: String): Flow<Result<List<LikeData>>> =
        dao.getAllLikeOfType(type = mediaType)
            .onStart {
                Log.e("LikeRepositoryImpl", "dao..onStart")
            }.map {
                Log.e("LikeRepositoryImpl", "dao.getAllLikeOfType ${it[0].imageUrl}")
                Result.success(it)
            }.catch {
                emit(Result.failure(it))
            }.flowOn(Dispatchers.IO)

    override suspend fun addLike(likeData: LikeData): Result<Unit> =
        try {
            dao.insert(likeData)
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