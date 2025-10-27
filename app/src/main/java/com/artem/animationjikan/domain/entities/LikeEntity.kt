package com.artem.animationjikan.domain.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "like")
data class LikeEntity(
    @PrimaryKey
    val mediaId: Int,
    val mediaType: String
)
