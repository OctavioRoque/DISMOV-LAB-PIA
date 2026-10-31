package com.example.pia_labdismov.core.database.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "lineups",
    foreignKeys = [
        ForeignKey(
            entity = TeamEntity::class,
            parentColumns = ["id"],
            childColumns = ["teamId"],
            onDelete = ForeignKey.CASCADE
        )
    ],
    indices = [Index("teamId")]
)
data class LineupEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val teamId: Long,
    val name: String,
    val systemType: String,
    val pos1PlayerId: Long,
    val pos2PlayerId: Long,
    val pos3PlayerId: Long,
    val pos4PlayerId: Long,
    val pos5PlayerId: Long,
    val pos6PlayerId: Long,
    val liberoPlayerId: Long?
)