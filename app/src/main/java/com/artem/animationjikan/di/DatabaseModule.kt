package com.artem.animationjikan.di

import android.content.Context
import androidx.room.Room
import com.artem.animationjikan.data.service.local.LikeDao
import com.artem.animationjikan.data.service.local.LikeDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import jakarta.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): LikeDatabase {
        return Room.databaseBuilder(
            context,
            LikeDatabase::class.java,
            "like_database"
        ).build()
    }

    @Provides
    fun provideLikeDao(database: LikeDatabase): LikeDao {
        return database.likeDao()
    }
}