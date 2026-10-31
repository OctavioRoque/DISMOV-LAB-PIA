package com.example.pia_labdismov.core.database.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "point_events",
    foreignKeys = [
        ForeignKey(
            entity = MatchSetEntity::class,
            parentColumns = ["id"],
            childColumns = ["matchSetId"],
            onDelete = ForeignKey.CASCADE
        )
    ],
    indices = [Index("matchSetId")]
)
data class PointEventEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val matchSetId: Long,
    val pointNumber: Int,
    val scoringTeamId: Long,
    val playerId: Long?,
    val actionType: String,
    val homeScoreAfter: Int,
    val awayScoreAfter: Int
)