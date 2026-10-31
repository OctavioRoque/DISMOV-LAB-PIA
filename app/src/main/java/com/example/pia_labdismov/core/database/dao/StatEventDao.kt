package com.example.pia_labdismov.core.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.pia_labdismov.core.database.entity.StatEventEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface StatEventDao {
    @Query("SELECT * FROM stat_events WHERE matchId = :matchId")
    fun getStatsForMatch(matchId: Long): Flow<List<StatEventEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertStat(stat: StatEventEntity): Long
}