package com.example.pia_labdismov.core.di

import android.content.Context
import androidx.room.Room
import com.example.pia_labdismov.core.database.VolleyCoachDatabase
import com.example.pia_labdismov.core.database.dao.PlayerDao
import com.example.pia_labdismov.core.database.dao.TeamDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideVolleyCoachDatabase(
        @ApplicationContext context: Context
    ): VolleyCoachDatabase {
        return Room.databaseBuilder(
            context,
            VolleyCoachDatabase::class.java,
            "volleycoach_db"
        )
        .fallbackToDestructiveMigration()
        .build()
    }

    @Provides
    fun provideTeamDao(database: VolleyCoachDatabase): TeamDao = database.teamDao()

    @Provides
    fun providePlayerDao(database: VolleyCoachDatabase): PlayerDao = database.playerDao()
}