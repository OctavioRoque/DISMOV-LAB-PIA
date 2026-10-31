package com.example.pia_labdismov.core.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.pia_labdismov.core.database.dao.PlayerDao
import com.example.pia_labdismov.core.database.dao.TeamDao
import com.example.pia_labdismov.core.database.dao.LineupDao
import com.example.pia_labdismov.core.database.dao.TacticBoardDao
import com.example.pia_labdismov.core.database.dao.TacticFrameDao
import com.example.pia_labdismov.core.database.dao.ExerciseDao
import com.example.pia_labdismov.core.database.dao.TrainingDao
import com.example.pia_labdismov.core.database.dao.StatEventDao
import com.example.pia_labdismov.core.database.dao.CompetitionDao
import com.example.pia_labdismov.core.database.dao.StandingDao
import com.example.pia_labdismov.core.database.entity.PlayerEntity
import com.example.pia_labdismov.core.database.entity.TeamEntity
import com.example.pia_labdismov.core.database.entity.MatchEntity
import com.example.pia_labdismov.core.database.entity.MatchSetEntity
import com.example.pia_labdismov.core.database.entity.PointEventEntity
import com.example.pia_labdismov.core.database.entity.LineupEntity
import com.example.pia_labdismov.core.database.entity.TacticBoardEntity
import com.example.pia_labdismov.core.database.entity.TacticFrameEntity
import com.example.pia_labdismov.core.database.entity.ExerciseEntity
import com.example.pia_labdismov.core.database.entity.TrainingSessionEntity
import com.example.pia_labdismov.core.database.entity.StatEventEntity
import com.example.pia_labdismov.core.database.entity.CompetitionEntity
import com.example.pia_labdismov.core.database.entity.StandingEntity

@Database(
    entities = [
        TeamEntity::class,
        PlayerEntity::class,
        MatchEntity::class,
        MatchSetEntity::class,
        PointEventEntity::class,
        LineupEntity::class,
        TacticBoardEntity::class,
        TacticFrameEntity::class,
        ExerciseEntity::class,
        TrainingSessionEntity::class,
        StatEventEntity::class,
        CompetitionEntity::class,
        StandingEntity::class
    ],
    version = 7,
    exportSchema = true
)
abstract class VolleyCoachDatabase : RoomDatabase() {
    abstract fun teamDao(): TeamDao
    abstract fun playerDao(): PlayerDao
    abstract fun lineupDao(): LineupDao
    abstract fun tacticBoardDao(): TacticBoardDao
    abstract fun tacticFrameDao(): TacticFrameDao
    abstract fun exerciseDao(): ExerciseDao
    abstract fun trainingDao(): TrainingDao
    abstract fun statEventDao(): StatEventDao
    abstract fun competitionDao(): CompetitionDao
    abstract fun standingDao(): StandingDao
}