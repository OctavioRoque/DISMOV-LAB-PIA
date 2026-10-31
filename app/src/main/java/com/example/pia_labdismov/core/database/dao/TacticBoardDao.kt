package com.example.pia_labdismov.core.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.pia_labdismov.core.database.entity.TacticBoardEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface TacticBoardDao {
    @Query("SELECT * FROM tactic_boards ORDER BY createdAt DESC")
    fun getAllBoards(): Flow<List<TacticBoardEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertBoard(board: TacticBoardEntity): Long

    @Delete
    suspend fun deleteBoard(board: TacticBoardEntity)
}