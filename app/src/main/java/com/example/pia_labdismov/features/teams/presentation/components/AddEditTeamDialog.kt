package com.example.pia_labdismov.features.teams.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.pia_labdismov.features.teams.domain.model.Team

private val ColorOptions = listOf(
    "#0D47A1", "#1976D2", "#D32F2F", "#388E3C",
    "#F57C00", "#7B1FA2", "#00796B", "#333333"
)

@Composable
fun AddEditTeamDialog(
    teamToEdit: Team?,
    onDismiss: () -> Unit,
    onSave: (Team) -> Unit
) {
    var name by remember { mutableStateOf(teamToEdit?.name ?: "") }
    var category by remember { mutableStateOf(teamToEdit?.category ?: "Primera División") }
    var gender by remember { mutableStateOf(teamToEdit?.gender ?: "Femenino") }
    var season by remember { mutableStateOf(teamToEdit?.season ?: "2026-I") }
    var selectedColor by remember { mutableStateOf(teamToEdit?.primaryColorHex ?: "#0D47A1") }

    AlertDialog(
        onDismissRequest = onDismiss,
        title = {
            Text(text = if (teamToEdit == null) "Nuevo Equipo" else "Editar Equipo")
        },
        text = {
            Column(modifier = Modifier.fillMaxWidth()) {
                OutlinedTextField(
                    value = name,
                    onValueChange = { name = it },
                    label = { Text("Nombre del equipo") },
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(8.dp))

                OutlinedTextField(
                    value = category,
                    onValueChange = { category = it },
                    label = { Text("Categoría (ej. Sub-18, Libres)") },
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(8.dp))

                OutlinedTextField(
                    value = gender,
                    onValueChange = { gender = it },
                    label = { Text("Rama (Femenino, Masculino, Mixto)") },
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(8.dp))

                OutlinedTextField(
                    value = season,
                    onValueChange = { season = it },
                    label = { Text("Temporada (ej. 2026)") },
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(12.dp))

                Text("Color del equipo:", style = MaterialTheme.typography.bodyMedium)
                Spacer(modifier = Modifier.height(6.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    ColorOptions.forEach { hex ->
                        val color = Color(android.graphics.Color.parseColor(hex))
                        val isSelected = hex.equals(selectedColor, ignoreCase = true)

                        Box(
                            modifier = Modifier
                                .size(32.dp)
                                .clip(CircleShape)
                                .background(color)
                                .border(
                                    width = if (isSelected) 3.dp else 0.dp,
                                    color = if (isSelected) MaterialTheme.colorScheme.onSurface else Color.Transparent,
                                    shape = CircleShape
                                )
                                .clickable { selectedColor = hex }
                        )
                    }
                }
            }
        },
        confirmButton = {
            TextButton(
                onClick = {
                    val team = Team(
                        id = teamToEdit?.id ?: 0L,
                        name = name.trim(),
                        category = category.trim(),
                        gender = gender.trim(),
                        season = season.trim(),
                        primaryColorHex = selectedColor
                    )
                    onSave(team)
                },
                enabled = name.isNotBlank()
            ) {
                Text("Guardar")
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text("Cancelar")
            }
        }
    )
}