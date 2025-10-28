package com.artem.animationjikan.data.service.local

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import jakarta.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    // 1. Database 인스턴스를 생성하는 Provider
    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): LikeDatabase {
        return Room.databaseBuilder(
            context,
            LikeDatabase::class.java,
            "like_database"
        ).build()
    }

    // 2. Database 인스턴스를 통해 LikeDao를 제공하는 Provider
    @Provides
    fun provideLikeDao(database: LikeDatabase): LikeDao {
        // ⭐️ Hilt는 제공된 database 객체를 사용하여 Dao를 얻습니다. ⭐️
        return database.likeDao()
    }
}