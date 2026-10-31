package com.example.pia_labdismov.features.tactics.domain.model

data class TacticBoard(
    val id: Long = 0,
    val title: String,
    val category: String = "Ataque", // Ataque, Recepción, Defensa, Rotación
    val description: String = "",
    val createdAt: Long = System.currentTimeMillis()
)