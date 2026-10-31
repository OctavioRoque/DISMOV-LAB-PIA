package com.example.pia_labdismov.features.match.domain.model

data class PointEvent(
    val id: Long = 0,
    val matchSetId: Long,
    val pointNumber: Int,
    val scoringTeamId: Long,
    val playerId: Long? = null,
    val actionType: ActionType,
    val homeScoreAfter: Int,
    val awayScoreAfter: Int
)