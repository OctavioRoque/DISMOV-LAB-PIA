package com.example.pia_labdismov.features.teams.domain.usecase

import com.example.pia_labdismov.features.teams.domain.model.DominantHand
import com.example.pia_labdismov.features.teams.domain.model.Player
import com.example.pia_labdismov.features.teams.domain.model.PlayerStatus
import com.example.pia_labdismov.features.teams.domain.model.Position
import com.example.pia_labdismov.features.teams.domain.repository.TeamRepository
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class SavePlayerUseCaseTest {

    private val repository: TeamRepository = mockk()
    private val savePlayerUseCase = SavePlayerUseCase(repository)

    @Test
    fun `savePlayer fails when name is empty`() = runBlocking {
        val player = Player(
            teamId = 1L,
            name = "",
            jerseyNumber = 7,
            position = Position.SETTER
        )

        val result = savePlayerUseCase(player)

        assertTrue(result.isFailure)
        assertEquals("El nombre del jugador debe tener al menos 2 caracteres", result.exceptionOrNull()?.message)
    }

    @Test
    fun `savePlayer fails when jersey number is invalid`() = runBlocking {
        val player = Player(
            teamId = 1L,
            name = "Juan Perez",
            jerseyNumber = 105, // Invalid > 99
            position = Position.SETTER
        )

        val result = savePlayerUseCase(player)

        assertTrue(result.isFailure)
        assertEquals("El número de camiseta debe estar entre 1 y 99", result.exceptionOrNull()?.message)
    }

    @Test
    fun `savePlayer fails when jersey number is duplicate in team`() = runBlocking {
        val existingPlayer = Player(
            id = 10L,
            teamId = 1L,
            name = "Maria Lopez",
            jerseyNumber = 7,
            position = Position.LIBERO
        )

        coEvery { repository.getPlayersListForTeam(1L) } returns listOf(existingPlayer)

        val newPlayer = Player(
            teamId = 1L,
            name = "Juan Perez",
            jerseyNumber = 7, // Duplicate jersey #7
            position = Position.SETTER
        )

        val result = savePlayerUseCase(newPlayer)

        assertTrue(result.isFailure)
        assertEquals("El número de camiseta #7 ya está en uso en este equipo", result.exceptionOrNull()?.message)
    }

    @Test
    fun `savePlayer succeeds when valid and jersey is unique`() = runBlocking {
        coEvery { repository.getPlayersListForTeam(1L) } returns emptyList()
        coEvery { repository.savePlayer(any()) } returns 100L

        val validPlayer = Player(
            teamId = 1L,
            name = "Carlos Santana",
            jerseyNumber = 10,
            position = Position.OUTSIDE_HITTER,
            heightCm = 190.0,
            dominantHand = DominantHand.RIGHT,
            status = PlayerStatus.ACTIVE
        )

        val result = savePlayerUseCase(validPlayer)

        assertTrue(result.isSuccess)
        assertEquals(100L, result.getOrNull())
        coVerify(exactly = 1) { repository.savePlayer(validPlayer) }
    }
}