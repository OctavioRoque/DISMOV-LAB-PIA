package com.example.pia_labdismov.core.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.pia_labdismov.features.home.presentation.HomeScreen
import com.example.pia_labdismov.features.teams.presentation.TeamsScreen
import com.example.pia_labdismov.features.tactics.presentation.TacticsScreen
import com.example.pia_labdismov.features.training.presentation.TrainingScreen
import com.example.pia_labdismov.features.stats.presentation.StatsScreen

@Composable
fun NavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Screen.Home.route
    ) {
        composable(Screen.Home.route) {
            HomeScreen(navController = navController)
        }
        composable(Screen.Teams.route) {
            TeamsScreen()
        }
        composable(Screen.Match.route) {
            GenericPlaceholderScreen("Partido en Vivo", "Módulo de Marcador (Fase 2)")
        }
        composable(Screen.Training.route) {
            TrainingScreen()
        }
        composable(Screen.Tactics.route) {
            TacticsScreen()
        }
        composable(Screen.Stats.route) {
            StatsScreen()
        }
    }
}