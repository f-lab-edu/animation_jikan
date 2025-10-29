package com.artem.animationjikan.domain.usecase

import com.artem.animationjikan.domain.entities.LikeEntity
import com.artem.animationjikan.domain.repository.LikeRepository
import javax.inject.Inject

class AddLikeUsecase @Inject constructor(
    private val likeRepository: LikeRepository
) {
    suspend fun execute(mediaId: Int, imageUrl: String, mediaType: String) {
        likeRepository.addLike(
            likeEntity = LikeEntity(
                mediaId = mediaId,
                imageUrl = imageUrl,
                mediaType = mediaType
            ),
        )
    }
}