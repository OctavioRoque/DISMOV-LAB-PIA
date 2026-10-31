package com.example.pia_labdismov.features.teams.presentation

import com.example.pia_labdismov.features.teams.domain.model.Player
import com.example.pia_labdismov.features.teams.domain.model.Team

data class TeamsUiState(
    val teams: List<Team> = emptyList(),
    val selectedTeam: Team? = null,
    val players: List<Player> = emptyList(),
    val isLoading: Boolean = false,
    val errorMessage: String? = null,
    val successMessage: String? = null,
    
    // Dialog states
    val isAddEditTeamDialogOpen: Boolean = false,
    val teamToEdit: Team? = null,
    
    val isAddEditPlayerDialogOpen: Boolean = false,
    val playerToEdit: Player? = null,

    val isCsvDialogOpen: Boolean = false,
    val exportedCsvText: String? = null
)