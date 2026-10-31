package com.example.pia_labdismov.core.database.mapper

import com.example.pia_labdismov.core.database.entity.PlayerEntity
import com.example.pia_labdismov.features.teams.domain.model.DominantHand
import com.example.pia_labdismov.features.teams.domain.model.Player
import com.example.pia_labdismov.features.teams.domain.model.PlayerStatus
import com.example.pia_labdismov.features.teams.domain.model.Position

fun PlayerEntity.toDomain(): Player {
    return Player(
        id = id,
        teamId = teamId,
        name = name,
        jerseyNumber = jerseyNumber,
        position = try { Position.valueOf(position) } catch (e: Exception) { Position.OUTSIDE_HITTER },
        heightCm = heightCm,
        dominantHand = try { DominantHand.valueOf(dominantHand) } catch (e: Exception) { DominantHand.RIGHT },
        photoUri = photoUri,
        notes = notes,
        status = try { PlayerStatus.valueOf(status) } catch (e: Exception) { PlayerStatus.ACTIVE }
    )
}

fun Player.toEntity(): PlayerEntity {
    return PlayerEntity(
        id = id,
        teamId = teamId,
        name = name,
        jerseyNumber = jerseyNumber,
        position = position.name,
        heightCm = heightCm,
        dominantHand = dominantHand.name,
        photoUri = photoUri,
        notes = notes,
        status = status.name
    )
}