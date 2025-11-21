package com.artem.animationjikan.domain.repository

import com.artem.animationjikan.domain.entities.RecentEntity
import kotlinx.coroutines.flow.Flow

interface RecentRepository {
    fun getRecent(): Flow<List<RecentEntity>>

    suspend fun addRecent(recentEntity: RecentEntity)

}