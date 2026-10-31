package com.example.pia_labdismov.features.teams.domain.usecase

import com.example.pia_labdismov.features.teams.domain.model.Team
import com.example.pia_labdismov.features.teams.domain.repository.TeamRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetTeamsUseCase @Inject constructor(
    private val repository: TeamRepository
) {
    operator fun invoke(): Flow<List<Team>> = repository.getAllTeams()
}