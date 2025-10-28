package com.artem.animationjikan.domain.usecase

import com.artem.animationjikan.domain.entities.HomeCommonEntity
import com.artem.animationjikan.domain.entities.LikeEntity
import com.artem.animationjikan.domain.repository.LikeRepository
import com.artem.animationjikan.util.enums.FilterCategory
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class LikeUsecase @Inject constructor(
    private val likeRepository: LikeRepository
) {
    /*fun getLikeOfType(mediaType: String): Flow<Result<List<Int>>> {
        val response = likeRepository.getAllLikeOfType(mediaType)
        return response
    }*/

    fun execute(mediaType: String): Flow<Result<List<LikeEntity>>> =
        likeRepository.getAllLikeOfType(mediaType = mediaType)


}