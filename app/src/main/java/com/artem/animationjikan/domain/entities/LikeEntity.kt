package com.artem.animationjikan.domain.entities

import com.artem.animationjikan.data.dto.LikeData

data class LikeEntity(
    val mediaId: Int,
    val imageUrl: String,
    val mediaType: String,
    val isLiked: Boolean
)

fun LikeEntity.toLikeData(): LikeData {
    return LikeData(
        mediaId = this.mediaId,
        imageUrl = this.imageUrl,
        mediaType = this.mediaType,
    )
}