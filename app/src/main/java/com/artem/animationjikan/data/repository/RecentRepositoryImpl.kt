package com.artem.animationjikan.data.repository

import android.util.Log
import com.artem.animationjikan.data.service.local.RecentDao
import com.artem.animationjikan.domain.entities.RecentEntity
import com.artem.animationjikan.domain.entities.toRecent
import com.artem.animationjikan.domain.repository.RecentRepository
import jakarta.inject.Inject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map

class RecentRepositoryImpl @Inject constructor(
    private val dao: RecentDao
) : RecentRepository {
    companion object {
        const val TAG: String = "RecentRepositoryImpl"
    }

    override fun getRecent(): Flow<List<RecentEntity>> {
        return dao.getRecentItems().map { list ->
            list.map { dto ->
                RecentEntity(
                    mediaId = dto.mediaId,
                    imageUrl = dto.imageUrl,
                    mediaType = dto.mediaType,
                )
            }
        }.catch { e ->
            Log.e(TAG, e.message.toString())
            throw e
        }.flowOn(Dispatchers.IO)
    }

    override suspend fun addRecent(recentEntity: RecentEntity) {
        if(dao.getCount() >= 20) {
            dao.deleteOldestItem()
        }

        dao.insert(recentEntity.toRecent())
    }

    override suspend fun removeRecent(mediaId: Int) {
        dao.delete(mediaId)
    }
}