package com.example.pia_labdismov.core.di

import com.example.pia_labdismov.features.teams.data.repository.TeamRepositoryImpl
import com.example.pia_labdismov.features.teams.domain.repository.TeamRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindTeamRepository(
        impl: TeamRepositoryImpl
    ): TeamRepository
}