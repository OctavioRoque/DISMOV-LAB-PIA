package com.example.pia_labdismov.core.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "teams")
data class TeamEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val name: String,
    val category: String,
    val gender: String,
    val season: String,
    val primaryColorHex: String,
    val logoUri: String?
)