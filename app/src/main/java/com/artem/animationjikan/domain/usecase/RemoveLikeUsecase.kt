package com.artem.animationjikan.domain.usecase

import com.artem.animationjikan.domain.repository.LikeRepository
import javax.inject.Inject

class RemoveLikeUsecase @Inject constructor(
    private val likeRepository: LikeRepository
) {
    suspend fun execute(mediaId: Int, imageUrl: String, mediaType: String) {
        likeRepository.updateLikeStatus(
            mediaId = mediaId,
            imageUrl = imageUrl,
            mediaType = mediaType,
            isLiked = true,
        )
    }
}