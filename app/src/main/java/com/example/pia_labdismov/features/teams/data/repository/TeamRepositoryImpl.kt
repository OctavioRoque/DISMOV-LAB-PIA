package com.example.pia_labdismov.features.teams.data.repository

import com.example.pia_labdismov.core.database.dao.PlayerDao
import com.example.pia_labdismov.core.database.dao.TeamDao
import com.example.pia_labdismov.core.database.mapper.toDomain
import com.example.pia_labdismov.core.database.mapper.toEntity
import com.example.pia_labdismov.features.teams.domain.model.Player
import com.example.pia_labdismov.features.teams.domain.model.Team
import com.example.pia_labdismov.features.teams.domain.repository.TeamRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class TeamRepositoryImpl @Inject constructor(
    private val teamDao: TeamDao,
    private val playerDao: PlayerDao
) : TeamRepository {

    override fun getAllTeams(): Flow<List<Team>> {
        return teamDao.getAllTeams().map { entities ->
            entities.map { it.toDomain() }
        }
    }

    override suspend fun getTeamById(id: Long): Team? {
        return teamDao.getTeamById(id)?.toDomain()
    }

    override suspend fun saveTeam(team: Team): Long {
        return teamDao.insertTeam(team.toEntity())
    }

    override suspend fun deleteTeam(team: Team) {
        teamDao.deleteTeam(team.toEntity())
    }

    override fun getPlayersForTeam(teamId: Long): Flow<List<Player>> {
        return playerDao.getPlayersForTeam(teamId).map { entities ->
            entities.map { it.toDomain() }
        }
    }

    override suspend fun getPlayersListForTeam(teamId: Long): List<Player> {
        return playerDao.getPlayersListForTeam(teamId).map { it.toDomain() }
    }

    override suspend fun getPlayerById(id: Long): Player? {
        return playerDao.getPlayerById(id)?.toDomain()
    }

    override suspend fun savePlayer(player: Player): Long {
        return playerDao.insertPlayer(player.toEntity())
    }

    override suspend fun savePlayers(players: List<Player>) {
        playerDao.insertPlayers(players.map { it.toEntity() })
    }

    override suspend fun deletePlayer(player: Player) {
        playerDao.deletePlayer(player.toEntity())
    }
}