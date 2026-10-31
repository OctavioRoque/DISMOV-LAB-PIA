package com.example.pia_labdismov.core.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.pia_labdismov.core.database.entity.TacticFrameEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface TacticFrameDao {
    @Query("SELECT * FROM tactic_frames WHERE boardId = :boardId ORDER BY frameIndex ASC")
    fun getFramesForBoard(boardId: Long): Flow<List<TacticFrameEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFrame(frame: TacticFrameEntity): Long
}