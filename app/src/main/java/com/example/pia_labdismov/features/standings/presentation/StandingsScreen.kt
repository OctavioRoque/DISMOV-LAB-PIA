package com.example.pia_labdismov.features.standings.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StandingsScreen() {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Tabla de Posiciones", fontWeight = FontWeight.Bold) },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.onPrimaryContainer
                )
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp)
        ) {
            Text(
                text = "Liga Metropolitana 2026",
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold
            )

            // Table Header
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text("#", fontWeight = FontWeight.Bold, modifier = Modifier.weight(0.5f))
                Text("Equipo", fontWeight = FontWeight.Bold, modifier = Modifier.weight(3f))
                Text("PJ", fontWeight = FontWeight.Bold, modifier = Modifier.weight(1f))
                Text("PG", fontWeight = FontWeight.Bold, modifier = Modifier.weight(1f))
                Text("PP", fontWeight = FontWeight.Bold, modifier = Modifier.weight(1f))
                Text("PTS", fontWeight = FontWeight.Bold, modifier = Modifier.weight(1.2f))
            }
            HorizontalDivider()

            // Sample rows
            LazyColumn(
                modifier = Modifier.fillMaxWidth()
            ) {
                items(3) { index ->
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 12.dp),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text("${index + 1}", modifier = Modifier.weight(0.5f))
                        Text("Equipo ${index + 1}", modifier = Modifier.weight(3f), fontWeight = FontWeight.SemiBold)
                        Text("${5 - index}", modifier = Modifier.weight(1f))
                        Text("${4 - index}", modifier = Modifier.weight(1f))
                        Text("$index", modifier = Modifier.weight(1f))
                        Text("${12 - (index * 3)}", modifier = Modifier.weight(1.2f), fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.primary)
                    }
                    HorizontalDivider(color = MaterialTheme.colorScheme.surfaceVariant)
                }
            }
        }
    }
}