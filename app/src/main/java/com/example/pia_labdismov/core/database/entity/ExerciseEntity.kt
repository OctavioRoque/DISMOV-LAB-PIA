package com.example.pia_labdismov.core.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "exercises")
data class ExerciseEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val name: String,
    val category: String,
    val durationMinutes: Int,
    val intensity: Int,
    val equipment: String,
    val description: String,
    val instructions: String,
    val isPreloaded: Boolean
)