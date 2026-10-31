package com.example.pia_labdismov.features.rotations.domain.model

data class Lineup(
    val id: Long = 0,
    val teamId: Long,
    val name: String, // e.g. "Titulares 5-1"
    val systemType: String = "5-1", // 5-1, 6-2, 4-2
    // Array of 6 player IDs for positions [IV, III, II, I, VI, V]
    val positions: List<Long> = listOf(0, 0, 0, 0, 0, 0),
    val liberoPlayerId: Long? = null
)