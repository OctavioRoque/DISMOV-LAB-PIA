package com.example.pia_labdismov.core.database.mapper

import com.example.pia_labdismov.core.database.entity.TeamEntity
import com.example.pia_labdismov.features.teams.domain.model.Team

fun TeamEntity.toDomain(): Team {
    return Team(
        id = id,
        name = name,
        category = category,
        gender = gender,
        season = season,
        primaryColorHex = primaryColorHex,
        logoUri = logoUri
    )
}

fun Team.toEntity(): TeamEntity {
    return TeamEntity(
        id = id,
        name = name,
        category = category,
        gender = gender,
        season = season,
        primaryColorHex = primaryColorHex,
        logoUri = logoUri
    )
}