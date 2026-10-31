package com.example.pia_labdismov.core.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.pia_labdismov.core.database.entity.CompetitionEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface CompetitionDao {
    @Query("SELECT * FROM competitions ORDER BY name ASC")
    fun getAllCompetitions(): Flow<List<CompetitionEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCompetition(competition: CompetitionEntity): Long
}