package com.example.pia_labdismov.features.training.domain.model

data class TrainingSession(
    val id: Long = 0,
    val teamId: Long,
    val date: String,
    val time: String,
    val durationTotalMinutes: Int,
    val location: String,
    val objective: String,
    val notes: String = ""
)