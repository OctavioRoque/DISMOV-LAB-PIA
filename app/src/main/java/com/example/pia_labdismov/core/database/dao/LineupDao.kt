package com.example.pia_labdismov.core.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.pia_labdismov.core.database.entity.LineupEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface LineupDao {
    @Query("SELECT * FROM lineups WHERE teamId = :teamId")
    fun getLineupsForTeam(teamId: Long): Flow<List<LineupEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertLineup(lineup: LineupEntity): Long

    @Delete
    suspend fun deleteLineup(lineup: LineupEntity)
}