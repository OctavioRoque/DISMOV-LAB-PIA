package com.example.pia_labdismov.features.teams.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pia_labdismov.features.teams.domain.model.Player
import com.example.pia_labdismov.features.teams.domain.model.Team
import com.example.pia_labdismov.features.teams.domain.usecase.DeletePlayerUseCase
import com.example.pia_labdismov.features.teams.domain.usecase.DeleteTeamUseCase
import com.example.pia_labdismov.features.teams.domain.usecase.ExportPlayersCsvUseCase
import com.example.pia_labdismov.features.teams.domain.usecase.GetPlayersUseCase
import com.example.pia_labdismov.features.teams.domain.usecase.GetTeamsUseCase
import com.example.pia_labdismov.features.teams.domain.usecase.ImportPlayersCsvUseCase
import com.example.pia_labdismov.features.teams.domain.usecase.SavePlayerUseCase
import com.example.pia_labdismov.features.teams.domain.usecase.SaveTeamUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TeamsViewModel @Inject constructor(
    private val getTeamsUseCase: GetTeamsUseCase,
    private val saveTeamUseCase: SaveTeamUseCase,
    private val deleteTeamUseCase: DeleteTeamUseCase,
    private val getPlayersUseCase: GetPlayersUseCase,
    private val savePlayerUseCase: SavePlayerUseCase,
    private val deletePlayerUseCase: DeletePlayerUseCase,
    private val importPlayersCsvUseCase: ImportPlayersCsvUseCase,
    private val exportPlayersCsvUseCase: ExportPlayersCsvUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(TeamsUiState())
    val uiState: StateFlow<TeamsUiState> = _uiState.asStateFlow()

    private var playersJob: Job? = null

    init {
        loadTeams()
    }

    private fun loadTeams() {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true) }
            getTeamsUseCase()
                .catch { error ->
                    _uiState.update { it.copy(isLoading = false, errorMessage = error.message) }
                }
                .collect { teams ->
                    _uiState.update { state ->
                        val selected = state.selectedTeam ?: teams.firstOrNull()
                        state.copy(
                            teams = teams,
                            selectedTeam = selected,
                            isLoading = false
                        )
                    }
                    val currentSelected = _uiState.value.selectedTeam
                    if (currentSelected != null) {
                        observePlayersForTeam(currentSelected.id)
                    }
                }
        }
    }

    fun selectTeam(team: Team) {
        _uiState.update { it.copy(selectedTeam = team) }
        observePlayersForTeam(team.id)
    }

    private fun observePlayersForTeam(teamId: Long) {
        playersJob?.cancel()
        playersJob = viewModelScope.launch {
            getPlayersUseCase(teamId)
                .catch { error ->
                    _uiState.update { it.copy(errorMessage = error.message) }
                }
                .collect { players ->
                    _uiState.update { it.copy(players = players) }
                }
        }
    }

    fun openAddTeamDialog() {
        _uiState.update { it.copy(isAddEditTeamDialogOpen = true, teamToEdit = null) }
    }

    fun openEditTeamDialog(team: Team) {
        _uiState.update { it.copy(isAddEditTeamDialogOpen = true, teamToEdit = team) }
    }

    fun closeTeamDialog() {
        _uiState.update { it.copy(isAddEditTeamDialogOpen = false, teamToEdit = null) }
    }

    fun saveTeam(team: Team) {
        viewModelScope.launch {
            saveTeamUseCase(team)
                .onSuccess { id ->
                    closeTeamDialog()
                    _uiState.update { it.copy(successMessage = "Equipo guardado correctamente") }
                    // Select if it's new
                    if (team.id == 0L) {
                        val newTeam = team.copy(id = id)
                        selectTeam(newTeam)
                    }
                }
                .onFailure { error ->
                    _uiState.update { it.copy(errorMessage = error.message ?: "Error al guardar el equipo") }
                }
        }
    }

    fun deleteTeam(team: Team) {
        viewModelScope.launch {
            deleteTeamUseCase(team)
            _uiState.update { state ->
                val remaining = state.teams.filter { it.id != team.id }
                val newSelected = remaining.firstOrNull()
                state.copy(
                    selectedTeam = newSelected,
                    successMessage = "Equipo eliminado"
                )
            }
        }
    }

    fun openAddPlayerDialog() {
        val selectedTeam = _uiState.value.selectedTeam ?: return
        _uiState.update {
            it.copy(
                isAddEditPlayerDialogOpen = true,
                playerToEdit = null
            )
        }
    }

    fun openEditPlayerDialog(player: Player) {
        _uiState.update {
            it.copy(
                isAddEditPlayerDialogOpen = true,
                playerToEdit = player
            )
        }
    }

    fun closePlayerDialog() {
        _uiState.update { it.copy(isAddEditPlayerDialogOpen = false, playerToEdit = null) }
    }

    fun savePlayer(player: Player) {
        viewModelScope.launch {
            savePlayerUseCase(player)
                .onSuccess {
                    closePlayerDialog()
                    _uiState.update { it.copy(successMessage = "Jugador guardado correctamente") }
                }
                .onFailure { error ->
                    _uiState.update { it.copy(errorMessage = error.message ?: "Error al guardar el jugador") }
                }
        }
    }

    fun deletePlayer(player: Player) {
        viewModelScope.launch {
            deletePlayerUseCase(player)
            _uiState.update { it.copy(successMessage = "Jugador eliminado") }
        }
    }

    fun openCsvDialog() {
        val selectedTeam = _uiState.value.selectedTeam ?: return
        viewModelScope.launch {
            val csv = exportPlayersCsvUseCase(selectedTeam.id)
            _uiState.update { it.copy(isCsvDialogOpen = true, exportedCsvText = csv) }
        }
    }

    fun closeCsvDialog() {
        _uiState.update { it.copy(isCsvDialogOpen = false, exportedCsvText = null) }
    }

    fun importCsv(csvContent: String) {
        val selectedTeam = _uiState.value.selectedTeam ?: return
        viewModelScope.launch {
            importPlayersCsvUseCase(selectedTeam.id, csvContent)
                .onSuccess { count ->
                    closeCsvDialog()
                    _uiState.update { it.copy(successMessage = "Se importaron $count jugadores con éxito") }
                }
                .onFailure { error ->
                    _uiState.update { it.copy(errorMessage = error.message ?: "Error al importar el CSV") }
                }
        }
    }

    fun clearMessages() {
        _uiState.update { it.copy(errorMessage = null, successMessage = null) }
    }
}