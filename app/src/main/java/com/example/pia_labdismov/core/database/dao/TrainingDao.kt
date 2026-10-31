package com.example.pia_labdismov.core.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.pia_labdismov.core.database.entity.TrainingSessionEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface TrainingDao {
    @Query("SELECT * FROM training_sessions WHERE teamId = :teamId ORDER BY date DESC")
    fun getSessionsForTeam(teamId: Long): Flow<List<TrainingSessionEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSession(session: TrainingSessionEntity): Long

    @Delete
    suspend fun deleteSession(session: TrainingSessionEntity)
}