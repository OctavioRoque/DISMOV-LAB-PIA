package com.example.pia_labdismov.features.standings.domain.model

data class Standing(
    val id: Long = 0,
    val competitionId: Long,
    val teamId: Long,
    val played: Int = 0,
    val won: Int = 0,
    val lost: Int = 0,
    val setsWon: Int = 0,
    val setsLost: Int = 0,
    val pointsWon: Int = 0,
    val pointsLost: Int = 0,
    val totalPoints: Int = 0
)