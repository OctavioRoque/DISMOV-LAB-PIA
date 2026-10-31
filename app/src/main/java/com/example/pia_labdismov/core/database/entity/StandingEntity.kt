package com.example.pia_labdismov.core.database.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "standings",
    foreignKeys = [
        ForeignKey(
            entity = CompetitionEntity::class,
            parentColumns = ["id"],
            childColumns = ["competitionId"],
            onDelete = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = TeamEntity::class,
            parentColumns = ["id"],
            childColumns = ["teamId"],
            onDelete = ForeignKey.CASCADE
        )
    ],
    indices = [Index("competitionId"), Index("teamId")]
)
data class StandingEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val competitionId: Long,
    val teamId: Long,
    val played: Int,
    val won: Int,
    val lost: Int,
    val setsWon: Int,
    val setsLost: Int,
    val pointsWon: Int,
    val pointsLost: Int,
    val totalPoints: Int
)