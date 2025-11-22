package com.artem.animationjikan.data.service.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.artem.animationjikan.data.dto.LikeData
import com.artem.animationjikan.data.dto.RecentItemData

@Database(
    entities = [LikeData::class, RecentItemData::class], // 좋아요 엔티티 목록
    version = 2,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun likeDao(): LikeDao

    abstract fun recentDao(): RecentDao
}