package com.example.pia_labdismov.features.standings.domain.model

data class Competition(
    val id: Long = 0,
    val name: String,
    val season: String,
    val description: String = ""
)