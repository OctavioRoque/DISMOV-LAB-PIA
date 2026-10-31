package com.example.pia_labdismov.core.database.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "matches",
    foreignKeys = [
        ForeignKey(
            entity = TeamEntity::class,
            parentColumns = ["id"],
            childColumns = ["homeTeamId"],
            onDelete = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = TeamEntity::class,
            parentColumns = ["id"],
            childColumns = ["awayTeamId"],
            onDelete = ForeignKey.CASCADE
        )
    ],
    indices = [Index("homeTeamId"), Index("awayTeamId")]
)
data class MatchEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val homeTeamId: Long,
    val awayTeamId: Long,
    val competitionId: Long?,
    val date: String,
    val bestOfSets: Int,
    val targetPointsNormalSet: Int,
    val targetPointsDecisiveSet: Int,
    val status: String,
    val homeSetsWon: Int,
    val awaySetsWon: Int,
    val currentSetNumber: Int,
    val servingTeamId: Long?
)