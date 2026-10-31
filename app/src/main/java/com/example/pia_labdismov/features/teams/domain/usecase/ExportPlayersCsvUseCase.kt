package com.example.pia_labdismov.features.teams.domain.usecase

import com.example.pia_labdismov.features.teams.domain.repository.TeamRepository
import javax.inject.Inject

class ExportPlayersCsvUseCase @Inject constructor(
    private val repository: TeamRepository
) {
    suspend operator fun invoke(teamId: Long): String {
        val players = repository.getPlayersListForTeam(teamId)
        val builder = StringBuilder()
        builder.append("Nombre,Numero,Posicion,Altura,Mano,Notas,Estado\n")
        for (p in players) {
            builder.append("${p.name},${p.jerseyNumber},${p.position.name},${p.heightCm},${p.dominantHand.name},${p.notes},${p.status.name}\n")
        }
        return builder.toString()
    }
}