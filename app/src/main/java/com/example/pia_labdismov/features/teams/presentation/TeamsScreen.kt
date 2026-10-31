package com.example.pia_labdismov.features.teams.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.FileDownload
import androidx.compose.material.icons.filled.Group
import androidx.compose.material.icons.filled.PersonAdd
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.pia_labdismov.features.teams.presentation.components.AddEditPlayerDialog
import com.example.pia_labdismov.features.teams.presentation.components.AddEditTeamDialog
import com.example.pia_labdismov.features.teams.presentation.components.CsvImportExportDialog
import com.example.pia_labdismov.features.teams.presentation.components.PlayerCard
import com.example.pia_labdismov.features.teams.presentation.components.TeamCard

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TeamsScreen(
    viewModel: TeamsViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()
    val snackbarHostState = remember { SnackbarHostState() }

    LaunchedEffect(uiState.errorMessage, uiState.successMessage) {
        uiState.errorMessage?.let { msg ->
            snackbarHostState.showSnackbar(msg)
            viewModel.clearMessages()
        }
        uiState.successMessage?.let { msg ->
            snackbarHostState.showSnackbar(msg)
            viewModel.clearMessages()
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Gestión de Equipos", fontWeight = FontWeight.Bold) },
                actions = {
                    if (uiState.selectedTeam != null) {
                        IconButton(onClick = { viewModel.openCsvDialog() }) {
                            Icon(Icons.Default.FileDownload, contentDescription = "Importar/Exportar CSV")
                        }
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.onPrimaryContainer
                )
            )
        },
        snackbarHost = { SnackbarHost(hostState = snackbarHostState) },
        floatingActionButton = {
            if (uiState.selectedTeam != null) {
                FloatingActionButton(
                    onClick = { viewModel.openAddPlayerDialog() },
                    containerColor = MaterialTheme.colorScheme.secondary,
                    contentColor = MaterialTheme.colorScheme.onSecondary
                ) {
                    Icon(Icons.Default.PersonAdd, contentDescription = "Añadir Jugador")
                }
            }
        }
    ) { padding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
        ) {
            if (uiState.isLoading) {
                CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
            } else if (uiState.teams.isEmpty()) {
                EmptyTeamsView(onAddTeam = { viewModel.openAddTeamDialog() })
            } else {
                TeamsContent(
                    uiState = uiState,
                    onSelectTeam = { viewModel.selectTeam(it) },
                    onAddTeam = { viewModel.openAddTeamDialog() },
                    onEditTeam = { viewModel.openEditTeamDialog(it) },
                    onDeleteTeam = { viewModel.deleteTeam(it) },
                    onEditPlayer = { viewModel.openEditPlayerDialog(it) },
                    onDeletePlayer = { viewModel.deletePlayer(it) }
                )
            }
        }

        // Dialogs
        if (uiState.isAddEditTeamDialogOpen) {
            AddEditTeamDialog(
                teamToEdit = uiState.teamToEdit,
                onDismiss = { viewModel.closeTeamDialog() },
                onSave = { team -> viewModel.saveTeam(team) }
            )
        }

        if (uiState.isAddEditPlayerDialogOpen && uiState.selectedTeam != null) {
            AddEditPlayerDialog(
                teamId = uiState.selectedTeam!!.id,
                playerToEdit = uiState.playerToEdit,
                onDismiss = { viewModel.closePlayerDialog() },
                onSave = { player -> viewModel.savePlayer(player) }
            )
        }

        if (uiState.isCsvDialogOpen) {
            CsvImportExportDialog(
                initialCsvText = uiState.exportedCsvText,
                onDismiss = { viewModel.closeCsvDialog() },
                onImport = { csvText -> viewModel.importCsv(csvText) }
            )
        }
    }
}

@Composable
private fun EmptyTeamsView(onAddTeam: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(32.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Icon(
            imageVector = Icons.Default.Group,
            contentDescription = null,
            modifier = Modifier.padding(16.dp),
            tint = MaterialTheme.colorScheme.primary
        )
        Text(
            text = "No tienes ningún equipo registrado",
            style = MaterialTheme.typography.titleLarge,
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = "Crea tu primer equipo para comenzar a gestionar tu plantilla de jugadores.",
            style = MaterialTheme.typography.bodyMedium,
            textAlign = TextAlign.Center,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
        Spacer(modifier = Modifier.height(24.dp))
        Button(onClick = onAddTeam) {
            Icon(Icons.Default.Add, contentDescription = null)
            Spacer(modifier = Modifier.padding(horizontal = 4.dp))
            Text("Crear Nuevo Equipo")
        }
    }
}

@Composable
private fun TeamsContent(
    uiState: TeamsUiState,
    onSelectTeam: (com.example.pia_labdismov.features.teams.domain.model.Team) -> Unit,
    onAddTeam: () -> Unit,
    onEditTeam: (com.example.pia_labdismov.features.teams.domain.model.Team) -> Unit,
    onDeleteTeam: (com.example.pia_labdismov.features.teams.domain.model.Team) -> Unit,
    onEditPlayer: (com.example.pia_labdismov.features.teams.domain.model.Player) -> Unit,
    onDeletePlayer: (com.example.pia_labdismov.features.teams.domain.model.Player) -> Unit
) {
    Column(modifier = Modifier.fillMaxSize()) {
        // Teams Selector Row
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 8.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "Equipos (${uiState.teams.size})",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold
            )
            OutlinedButton(onClick = onAddTeam) {
                Icon(Icons.Default.Add, contentDescription = null)
                Text("Añadir Equipo")
            }
        }

        LazyRow(
            contentPadding = PaddingValues(horizontal = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            items(uiState.teams, key = { it.id }) { team ->
                Box(modifier = Modifier.width(280.dp)) {
                    TeamCard(
                        team = team,
                        isSelected = team.id == uiState.selectedTeam?.id,
                        onSelect = { onSelectTeam(team) },
                        onEdit = { onEditTeam(team) },
                        onDelete = { onDeleteTeam(team) }
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Players Section
        val selectedTeam = uiState.selectedTeam
        if (selectedTeam != null) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 4.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "Plantilla: ${selectedTeam.name} (${uiState.players.size} jugadores)",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.primary
                )
            }

            if (uiState.players.isEmpty()) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f)
                        .padding(32.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "No hay jugadores en este equipo. Presiona el botón + para añadir un jugador.",
                        textAlign = TextAlign.Center,
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            } else {
                LazyColumn(
                    contentPadding = PaddingValues(start = 16.dp, end = 16.dp, top = 8.dp, bottom = 80.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp),
                    modifier = Modifier.fillMaxSize()
                ) {
                    items(uiState.players, key = { it.id }) { player ->
                        PlayerCard(
                            player = player,
                            onEdit = { onEditPlayer(player) },
                            onDelete = { onDeletePlayer(player) }
                        )
                    }
                }
            }
        }
    }
}