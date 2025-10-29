package com.artem.animationjikan.data.service.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.artem.animationjikan.domain.entities.LikeData

@Database(
    entities = [LikeData::class], // 좋아요 엔티티 목록
    version = 1,
    exportSchema = false
)
abstract class LikeDatabase : RoomDatabase() {
    abstract fun likeDao(): LikeDao
}