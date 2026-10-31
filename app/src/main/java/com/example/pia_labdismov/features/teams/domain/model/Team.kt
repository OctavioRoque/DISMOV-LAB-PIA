package com.example.pia_labdismov.features.teams.domain.model

data class Team(
    val id: Long = 0,
    val name: String,
    val category: String, // e.g. "Primera División", "Sub-18"
    val gender: String,   // e.g. "Femenino", "Masculino", "Mixto"
    val season: String,   // e.g. "2026-I"
    val primaryColorHex: String = "#0D47A1",
    val logoUri: String? = null
)