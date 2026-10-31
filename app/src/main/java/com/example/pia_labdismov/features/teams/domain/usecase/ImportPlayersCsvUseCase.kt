package com.example.pia_labdismov.features.teams.domain.usecase

import com.example.pia_labdismov.core.util.PlayerValidationUtil
import com.example.pia_labdismov.features.teams.domain.model.DominantHand
import com.example.pia_labdismov.features.teams.domain.model.Player
import com.example.pia_labdismov.features.teams.domain.model.PlayerStatus
import com.example.pia_labdismov.features.teams.domain.model.Position
import com.example.pia_labdismov.features.teams.domain.repository.TeamRepository
import javax.inject.Inject

class ImportPlayersCsvUseCase @Inject constructor(
    private val repository: TeamRepository
) {
    suspend operator fun invoke(teamId: Long, csvContent: String): Result<Int> {
        val lines = csvContent.lines().filter { it.isNotBlank() }
        if (lines.isEmpty()) {
            return Result.failure(IllegalArgumentException("El archivo CSV está vacío"))
        }

        // Check header
        val startIndex = if (lines[0].lowercase().contains("nombre") || lines[0].lowercase().contains("name")) 1 else 0

        val importedPlayers = mutableListOf<Player>()
        val existingPlayers = repository.getPlayersListForTeam(teamId)
        val existingJerseys = existingPlayers.map { it.jerseyNumber }.toMutableSet()

        for (i in startIndex until lines.size) {
            val line = lines[i]
            val parts = line.split(",").map { it.trim() }
            if (parts.size < 2) continue

            val name = parts[0]
            val jerseyNumber = parts[1].toIntOrNull() ?: continue

            if (!PlayerValidationUtil.isValidJerseyNumber(jerseyNumber)) continue
            if (existingJerseys.contains(jerseyNumber)) continue // Skip duplicate jersey numbers in import

            val position = if (parts.size > 2) parsePosition(parts[2]) else Position.OUTSIDE_HITTER
            val heightCm = if (parts.size > 3) parts[3].toDoubleOrNull() ?: 0.0 else 0.0
            val dominantHand = if (parts.size > 4) parseHand(parts[4]) else DominantHand.RIGHT
            val notes = if (parts.size > 5) parts[5] else ""
            val status = if (parts.size > 6) parseStatus(parts[6]) else PlayerStatus.ACTIVE

            val player = Player(
                teamId = teamId,
                name = name,
                jerseyNumber = jerseyNumber,
                position = position,
                heightCm = heightCm,
                dominantHand = dominantHand,
                notes = notes,
                status = status
            )

            importedPlayers.add(player)
            existingJerseys.add(jerseyNumber)
        }

        if (importedPlayers.isNotEmpty()) {
            repository.savePlayers(importedPlayers)
        }

        return Result.success(importedPlayers.size)
    }

    private fun parsePosition(value: String): Position {
        return try {
            Position.valueOf(value.uppercase())
        } catch (e: Exception) {
            when (value.lowercase()) {
                "armador", "pasador", "setter" -> Position.SETTER
                "opuesto", "opposite" -> Position.OPPOSITE
                "central", "middle" -> Position.MIDDLE_BLOCKER
                "libero", "líbero" -> Position.LIBERO
                else -> Position.OUTSIDE_HITTER
            }
        }
    }

    private fun parseHand(value: String): DominantHand {
        return when (value.lowercase()) {
            "zurdo", "left" -> DominantHand.LEFT
            "ambidiestro", "ambidextrous" -> DominantHand.AMBIDEXTROUS
            else -> DominantHand.RIGHT
        }
    }

    private fun parseStatus(value: String): PlayerStatus {
        return when (value.lowercase()) {
            "lesionado", "injured" -> PlayerStatus.INJURED
            "inactivo", "inactive" -> PlayerStatus.INACTIVE
            else -> PlayerStatus.ACTIVE
        }
    }
}