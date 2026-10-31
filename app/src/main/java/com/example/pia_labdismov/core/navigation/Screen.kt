package com.example.pia_labdismov.core.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.BarChart
import androidx.compose.material.icons.filled.Brush
import androidx.compose.material.icons.filled.FitnessCenter
import androidx.compose.material.icons.filled.Group
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.SportsVolleyball
import androidx.compose.ui.graphics.vector.ImageVector

sealed class Screen(
    val route: String,
    val title: String,
    val icon: ImageVector
) {
    object Home : Screen("home", "Inicio", Icons.Default.Home)
    object Teams : Screen("teams", "Equipos", Icons.Default.Group)
    object Match : Screen("match", "Partido", Icons.Default.SportsVolleyball)
    object Training : Screen("training", "Entrenos", Icons.Default.FitnessCenter)
    object Tactics : Screen("tactics", "Pizarra", Icons.Default.Brush)
    object Stats : Screen("stats", "Stats", Icons.Default.BarChart)
}