package com.example.pia_labdismov.features.teams.domain.model

data class Player(
    val id: Long = 0,
    val teamId: Long,
    val name: String,
    val jerseyNumber: Int,
    val position: Position,
    val heightCm: Double = 0.0,
    val dominantHand: DominantHand = DominantHand.RIGHT,
    val photoUri: String? = null,
    val notes: String = "",
    val status: PlayerStatus = PlayerStatus.ACTIVE
)