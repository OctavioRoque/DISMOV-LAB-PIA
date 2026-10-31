package com.example.pia_labdismov.features.teams.domain.usecase

import com.example.pia_labdismov.features.teams.domain.model.Player
import com.example.pia_labdismov.features.teams.domain.repository.TeamRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetPlayersUseCase @Inject constructor(
    private val repository: TeamRepository
) {
    operator fun invoke(teamId: Long): Flow<List<Player>> = repository.getPlayersForTeam(teamId)
}