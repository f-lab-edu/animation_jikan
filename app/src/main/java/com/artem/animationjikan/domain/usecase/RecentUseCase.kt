package com.artem.animationjikan.domain.usecase

import com.artem.animationjikan.domain.entities.RecentEntity
import com.artem.animationjikan.domain.repository.RecentRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class RecentUseCase @Inject constructor(
    private val recentRepository: RecentRepository
) {

    fun execute(): Flow<List<RecentEntity>> = recentRepository.getRecent()

    suspend fun addRecent(recentEntity: RecentEntity): Result<Unit> {
        return runCatching {
            recentRepository.addRecent(recentEntity = recentEntity)
        }
    }

    suspend fun removeRecent(malId: Int): Result<Unit> {
        return runCatching {
            recentRepository.removeRecent(mediaId = malId)
        }
    }
}