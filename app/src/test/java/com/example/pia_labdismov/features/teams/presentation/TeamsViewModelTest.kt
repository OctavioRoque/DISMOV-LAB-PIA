package com.example.pia_labdismov.features.teams.presentation

import app.cash.turbine.test
import com.example.pia_labdismov.features.teams.domain.model.Team
import com.example.pia_labdismov.features.teams.domain.usecase.DeletePlayerUseCase
import com.example.pia_labdismov.features.teams.domain.usecase.DeleteTeamUseCase
import com.example.pia_labdismov.features.teams.domain.usecase.ExportPlayersCsvUseCase
import com.example.pia_labdismov.features.teams.domain.usecase.GetPlayersUseCase
import com.example.pia_labdismov.features.teams.domain.usecase.GetTeamsUseCase
import com.example.pia_labdismov.features.teams.domain.usecase.ImportPlayersCsvUseCase
import com.example.pia_labdismov.features.teams.domain.usecase.SavePlayerUseCase
import com.example.pia_labdismov.features.teams.domain.usecase.SaveTeamUseCase
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class TeamsViewModelTest {

    private val testDispatcher = StandardTestDispatcher()

    private val getTeamsUseCase: GetTeamsUseCase = mockk()
    private val saveTeamUseCase: SaveTeamUseCase = mockk()
    private val deleteTeamUseCase: DeleteTeamUseCase = mockk()
    private val getPlayersUseCase: GetPlayersUseCase = mockk()
    private val savePlayerUseCase: SavePlayerUseCase = mockk()
    private val deletePlayerUseCase: DeletePlayerUseCase = mockk()
    private val importPlayersCsvUseCase: ImportPlayersCsvUseCase = mockk()
    private val exportPlayersCsvUseCase: ExportPlayersCsvUseCase = mockk()

    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `init loads teams and selects first team`() = runTest {
        val testTeam = Team(id = 1L, name = "Eagles", category = "Sub-20", gender = "Varonil", season = "2026")
        coEvery { getTeamsUseCase() } returns flowOf(listOf(testTeam))
        coEvery { getPlayersUseCase(1L) } returns flowOf(emptyList())

        val viewModel = TeamsViewModel(
            getTeamsUseCase, saveTeamUseCase, deleteTeamUseCase,
            getPlayersUseCase, savePlayerUseCase, deletePlayerUseCase,
            importPlayersCsvUseCase, exportPlayersCsvUseCase
        )

        testDispatcher.scheduler.advanceUntilIdle()

        viewModel.uiState.test {
            val state = awaitItem()
            assertEquals(1, state.teams.size)
            assertEquals("Eagles", state.selectedTeam?.name)
        }
    }

    @Test
    fun `saveTeam shows success message when valid`() = runTest {
        coEvery { getTeamsUseCase() } returns flowOf(emptyList())
        val teamToSave = Team(name = "Tigres", category = "Primera", gender = "Femenino", season = "2026")
        coEvery { saveTeamUseCase(teamToSave) } returns Result.success(5L)
        coEvery { getPlayersUseCase(5L) } returns flowOf(emptyList())

        val viewModel = TeamsViewModel(
            getTeamsUseCase, saveTeamUseCase, deleteTeamUseCase,
            getPlayersUseCase, savePlayerUseCase, deletePlayerUseCase,
            importPlayersCsvUseCase, exportPlayersCsvUseCase
        )

        testDispatcher.scheduler.advanceUntilIdle()

        viewModel.saveTeam(teamToSave)

        testDispatcher.scheduler.advanceUntilIdle()

        viewModel.uiState.test {
            val state = awaitItem()
            assertEquals("Equipo guardado correctamente", state.successMessage)
        }
    }
}