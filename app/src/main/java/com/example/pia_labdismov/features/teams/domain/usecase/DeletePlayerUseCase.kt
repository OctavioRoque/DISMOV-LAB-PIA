package com.example.pia_labdismov.features.teams.domain.usecase

import com.example.pia_labdismov.features.teams.domain.model.Player
import com.example.pia_labdismov.features.teams.domain.repository.TeamRepository
import javax.inject.Inject

class DeletePlayerUseCase @Inject constructor(
    private val repository: TeamRepository
) {
    suspend operator fun invoke(player: Player) {
        repository.deletePlayer(player)
    }
}