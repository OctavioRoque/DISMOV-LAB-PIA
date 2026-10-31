package com.example.pia_labdismov.features.tactics.domain.model

data class TacticFrame(
    val id: Long = 0,
    val boardId: Long,
    val frameIndex: Int,
    val tokensJson: String, // JSON serialized list of TacticalToken
    val pathsJson: String   // JSON serialized drawing paths
)