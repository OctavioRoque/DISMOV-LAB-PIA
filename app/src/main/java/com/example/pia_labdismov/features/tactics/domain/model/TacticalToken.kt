package com.example.pia_labdismov.features.tactics.domain.model

data class TacticalToken(
    val id: String,
    val type: TokenType,
    val label: String, // e.g. "1", "S", "Ball"
    val x: Float,      // Normalized 0.0 to 1.0 on court
    val y: Float,      // Normalized 0.0 to 1.0 on court
    val colorHex: String = "#0D47A1"
)

enum class TokenType {
    PLAYER_HOME,
    PLAYER_AWAY,
    BALL,
    CONE,
    MARKER
}