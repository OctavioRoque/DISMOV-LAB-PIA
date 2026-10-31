package com.example.pia_labdismov.features.stats.domain.model

data class StatEvent(
    val id: Long = 0,
    val matchId: Long,
    val playerId: Long,
    val setNumber: Int,
    val skillType: SkillType,
    val evaluation: Int // e.g. 0 to 3 for reception, or 1 for success, -1 for error
)

enum class SkillType {
    SERVE,
    RECEPTION,
    SETTING,
    ATTACK,
    BLOCK,
    DEFENSE
}