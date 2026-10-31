package com.example.pia_labdismov.features.match.domain.model

enum class ActionType(val displayName: String, val isPointForUs: Boolean) {
    ACE("Saque Directo (Ace)", true),
    ATTACK_KILL("Ataque Exitoso (Kill)", true),
    BLOCK_POINT("Bloqueo Exitoso", true),
    OPPONENT_ERROR("Error del Rival", true),
    OWN_ERROR("Error Propio", false),
    OTHER("Otra Acción", true)
}