package com.example.pia_labdismov.features.match.domain.model

data class MatchSet(
    val id: Long = 0,
    val matchId: Long,
    val setNumber: Int,
    val homeScore: Int = 0,
    val awayScore: Int = 0,
    val winnerTeamId: Long? = null,
    val isCompleted: Boolean = false
)