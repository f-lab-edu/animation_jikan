package com.artem.animationjikan.data.service.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.artem.animationjikan.data.dto.RecentItemData
import kotlinx.coroutines.flow.Flow

@Dao
interface RecentDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(entity: RecentItemData)

    @Query("SELECT * FROM `recent` ORDER BY accessTime DESC LIMIT 20")
    fun getRecentItems(): Flow<List<RecentItemData>>

    @Query("SELECT COUNT(mediaId) FROM recent")
    suspend fun getCount(): Int

    @Query("DELETE FROM recent WHERE mediaId IN (SELECT mediaId FROM recent ORDER BY accessTime ASC LIMIT 1)")
    suspend fun deleteOldestItem()

}