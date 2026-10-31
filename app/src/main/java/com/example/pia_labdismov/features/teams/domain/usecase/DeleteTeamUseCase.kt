package com.example.pia_labdismov.features.teams.domain.usecase

import com.example.pia_labdismov.features.teams.domain.model.Team
import com.example.pia_labdismov.features.teams.domain.repository.TeamRepository
import javax.inject.Inject

class DeleteTeamUseCase @Inject constructor(
    private val repository: TeamRepository
) {
    suspend operator fun invoke(team: Team) {
        repository.deleteTeam(team)
    }
}