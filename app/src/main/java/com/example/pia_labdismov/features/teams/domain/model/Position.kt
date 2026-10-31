package com.example.pia_labdismov.features.teams.domain.model

enum class Position(val displayName: String, val abbreviation: String) {
    SETTER("Armador / Pasador", "ARM"),
    OPPOSITE("Opuesto", "OPU"),
    OUTSIDE_HITTER("Receptor / Punta", "REC"),
    MIDDLE_BLOCKER("Central", "CEN"),
    LIBERO("Líbero", "LIB")
}