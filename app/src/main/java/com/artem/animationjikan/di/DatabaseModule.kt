package com.artem.animationjikan.di

import android.content.Context
import androidx.room.Room
import com.artem.animationjikan.data.service.local.AppDatabase
import com.artem.animationjikan.data.service.local.LikeDao
import com.artem.animationjikan.data.service.local.RecentDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "jikan_database"
        ).build()
    }

    @Provides
    @Singleton
    fun provideLikeDao(database: AppDatabase): LikeDao {
        return database.likeDao()
    }

    @Provides
    @Singleton
    fun provideRecentDao(database: AppDatabase): RecentDao {
        return database.recentDao()
    }
}