package com.artem.animationjikan.data.service.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.artem.animationjikan.data.dto.LikeData
import kotlinx.coroutines.flow.Flow

@Dao
interface LikeDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(entity: LikeData)

    @Query("DELETE FROM `like` WHERE mediaId = :mediaId")
    suspend fun delete(mediaId: Int)

    @Query("SELECT EXISTS(SELECT 1 FROM `like` WHERE mediaId = :mediaId)")
    fun isLike(mediaId: Int): Flow<Boolean>

    @Query("SELECT * FROM `like` WHERE mediaType = :type")
    fun getLikesList(type: String?): Flow<List<LikeData>>

    @Query("SELECT * FROM `like`")
    fun getAllLikes(): Flow<List<LikeData>>
}