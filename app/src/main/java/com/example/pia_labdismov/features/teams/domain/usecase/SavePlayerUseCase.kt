package com.example.pia_labdismov.features.teams.domain.usecase

import com.example.pia_labdismov.core.util.PlayerValidationUtil
import com.example.pia_labdismov.features.teams.domain.model.Player
import com.example.pia_labdismov.features.teams.domain.repository.TeamRepository
import javax.inject.Inject

class SavePlayerUseCase @Inject constructor(
    private val repository: TeamRepository
) {
    suspend operator fun invoke(player: Player): Result<Long> {
        // Validate player name using Java utility
        if (!PlayerValidationUtil.isValidPlayerName(player.name)) {
            return Result.failure(IllegalArgumentException("El nombre del jugador debe tener al menos 2 caracteres"))
        }

        // Validate jersey number legal range (1-99 FIVB)
        if (!PlayerValidationUtil.isValidJerseyNumber(player.jerseyNumber)) {
            return Result.failure(IllegalArgumentException("El número de camiseta debe estar entre 1 y 99"))
        }

        // Validate height
        if (!PlayerValidationUtil.isValidHeight(player.heightCm)) {
            return Result.failure(IllegalArgumentException("La altura debe estar entre 100cm y 250cm"))
        }

        // Check jersey number uniqueness in team using Java validation engine
        val currentTeamPlayers = repository.getPlayersListForTeam(player.teamId)
        val existingNumbers = currentTeamPlayers.map { it.jerseyNumber }
        val playerIds = currentTeamPlayers.map { it.id }

        val isUnique = PlayerValidationUtil.isJerseyNumberUnique(
            player.jerseyNumber,
            existingNumbers,
            player.id,
            playerIds
        )

        if (!isUnique) {
            return Result.failure(IllegalArgumentException("El número de camiseta #${player.jerseyNumber} ya está en uso en este equipo"))
        }

        val id = repository.savePlayer(player)
        return Result.success(id)
    }
}