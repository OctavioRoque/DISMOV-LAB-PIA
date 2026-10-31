package com.example.pia_labdismov.features.teams.domain.usecase

import com.example.pia_labdismov.features.teams.domain.model.Team
import com.example.pia_labdismov.features.teams.domain.repository.TeamRepository
import javax.inject.Inject

class SaveTeamUseCase @Inject constructor(
    private val repository: TeamRepository
) {
    suspend operator fun invoke(team: Team): Result<Long> {
        if (team.name.isBlank()) {
            return Result.failure(IllegalArgumentException("El nombre del equipo no puede estar vacío"))
        }
        val id = repository.saveTeam(team)
        return Result.success(id)
    }
}