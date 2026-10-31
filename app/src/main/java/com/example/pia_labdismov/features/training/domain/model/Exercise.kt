package com.example.pia_labdismov.features.training.domain.model

data class Exercise(
    val id: Long = 0,
    val name: String,
    val category: String, // Calentamiento, Saque, Recepción, Armado, Ataque, Bloqueo, Defensa, Físico, Juego
    val durationMinutes: Int,
    val intensity: Int, // 1 to 5
    val equipment: String,
    val description: String,
    val instructions: String,
    val isPreloaded: Boolean = true
)