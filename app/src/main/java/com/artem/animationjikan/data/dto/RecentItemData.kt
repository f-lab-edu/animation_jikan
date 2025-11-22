package com.artem.animationjikan.data.dto

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "recent")
data class RecentItemData(
    @PrimaryKey
    val mediaId: Int,
    val imageUrl: String?,
    val mediaType: String,
    val accessTime: Long = System.currentTimeMillis(),
)