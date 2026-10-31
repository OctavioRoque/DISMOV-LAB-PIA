package com.example.pia_labdismov.features.match.domain.model

data class Match(
    val id: Long = 0,
    val homeTeamId: Long,
    val awayTeamId: Long,
    val competitionId: Long? = null,
    val date: String,
    val bestOfSets: Int = 5, // 5 (FIVB) or 3
    val targetPointsNormalSet: Int = 25,
    val targetPointsDecisiveSet: Int = 15,
    val status: MatchStatus = MatchStatus.IN_PROGRESS,
    val homeSetsWon: Int = 0,
    val awaySetsWon: Int = 0,
    val currentSetNumber: Int = 1,
    val servingTeamId: Long? = null
)

enum class MatchStatus {
    SCHEDULED,
    IN_PROGRESS,
    PAUSED,
    COMPLETED
}