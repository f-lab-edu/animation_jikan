package com.artem.animationjikan.domain.entities

import com.artem.animationjikan.data.dto.RecentItemData

data class RecentEntity(
    val mediaId: Int,
    val imageUrl: String?,
    val mediaType: String,
)

fun RecentEntity.toRecent(): RecentItemData {
    return RecentItemData(
        mediaId = mediaId,
        imageUrl = imageUrl,
        mediaType = mediaType,
    )
}