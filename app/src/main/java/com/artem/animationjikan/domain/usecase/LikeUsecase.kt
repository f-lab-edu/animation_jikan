package com.artem.animationjikan.domain.usecase

import com.artem.animationjikan.domain.repository.LikeRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class LikeUsecase @Inject constructor(
    private val likeRepository: LikeRepository
){
    suspend fun getLikeOfType(mediaType: String): Flow<List<Int>> {
        val response = likeRepository.getAllLikeOfType(mediaType)
        return response
    }
}