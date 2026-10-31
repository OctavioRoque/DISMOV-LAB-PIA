package com.example.pia_labdismov.core.database.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "tactic_frames",
    foreignKeys = [
        ForeignKey(
            entity = TacticBoardEntity::class,
            parentColumns = ["id"],
            childColumns = ["boardId"],
            onDelete = ForeignKey.CASCADE
        )
    ],
    indices = [Index("boardId")]
)
data class TacticFrameEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val boardId: Long,
    val frameIndex: Int,
    val tokensJson: String,
    val pathsJson: String
)