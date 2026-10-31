package com.example.pia_labdismov.features.teams.domain.repository

import com.example.pia_labdismov.features.teams.domain.model.Player
import com.example.pia_labdismov.features.teams.domain.model.Team
import kotlinx.coroutines.flow.Flow

interface TeamRepository {
    fun getAllTeams(): Flow<List<Team>>
    suspend fun getTeamById(id: Long): Team?
    suspend fun saveTeam(team: Team): Long
    suspend fun deleteTeam(team: Team)

    fun getPlayersForTeam(teamId: Long): Flow<List<Player>>
    suspend fun getPlayersListForTeam(teamId: Long): List<Player>
    suspend fun getPlayerById(id: Long): Player?
    suspend fun savePlayer(player: Player): Long
    suspend fun savePlayers(players: List<Player>)
    suspend fun deletePlayer(player: Player)
}