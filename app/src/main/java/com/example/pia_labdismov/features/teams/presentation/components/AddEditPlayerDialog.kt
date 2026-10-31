package com.example.pia_labdismov.features.teams.presentation.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.example.pia_labdismov.features.teams.domain.model.DominantHand
import com.example.pia_labdismov.features.teams.domain.model.Player
import com.example.pia_labdismov.features.teams.domain.model.PlayerStatus
import com.example.pia_labdismov.features.teams.domain.model.Position

@Composable
fun AddEditPlayerDialog(
    teamId: Long,
    playerToEdit: Player?,
    onDismiss: () -> Unit,
    onSave: (Player) -> Unit
) {
    var name by remember { mutableStateOf(playerToEdit?.name ?: "") }
    var jerseyNumberStr by remember { mutableStateOf(playerToEdit?.jerseyNumber?.toString() ?: "") }
    var selectedPosition by remember { mutableStateOf(playerToEdit?.position ?: Position.OUTSIDE_HITTER) }
    var heightCmStr by remember { mutableStateOf(if ((playerToEdit?.heightCm ?: 0.0) > 0) playerToEdit?.heightCm?.toInt().toString() else "") }
    var selectedHand by remember { mutableStateOf(playerToEdit?.dominantHand ?: DominantHand.RIGHT) }
    var notes by remember { mutableStateOf(playerToEdit?.notes ?: "") }
    var selectedStatus by remember { mutableStateOf(playerToEdit?.status ?: PlayerStatus.ACTIVE) }

    var isPositionDropdownExpanded by remember { mutableStateOf(false) }

    AlertDialog(
        onDismissRequest = onDismiss,
        title = {
            Text(text = if (playerToEdit == null) "Nuevo Jugador" else "Editar Jugador")
        },
        text = {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .verticalScroll(rememberScrollState())
            ) {
                OutlinedTextField(
                    value = name,
                    onValueChange = { name = it },
                    label = { Text("Nombre completo *") },
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(8.dp))

                OutlinedTextField(
                    value = jerseyNumberStr,
                    onValueChange = { if (it.length <= 2 && it.all { char -> char.isDigit() }) jerseyNumberStr = it },
                    label = { Text("Número de Camiseta (1-99) *") },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(8.dp))

                // Position Selector
                Text("Posición en cancha *", style = MaterialTheme.typography.bodyMedium)
                Box(modifier = Modifier.fillMaxWidth()) {
                    OutlinedTextField(
                        value = "${selectedPosition.displayName} (${selectedPosition.abbreviation})",
                        onValueChange = {},
                        readOnly = true,
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable { isPositionDropdownExpanded = true },
                        enabled = false
                    )
                    DropdownMenu(
                        expanded = isPositionDropdownExpanded,
                        onDismissRequest = { isPositionDropdownExpanded = false }
                    ) {
                        Position.values().forEach { pos ->
                            DropdownMenuItem(
                                text = { Text("${pos.displayName} (${pos.abbreviation})") },
                                onClick = {
                                    selectedPosition = pos
                                    isPositionDropdownExpanded = false
                                }
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.height(8.dp))

                OutlinedTextField(
                    value = heightCmStr,
                    onValueChange = { heightCmStr = it.filter { char -> char.isDigit() } },
                    label = { Text("Altura en cm (opcional)") },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text("Mano dominante:", style = MaterialTheme.typography.bodyMedium)
                Row(verticalAlignment = Alignment.CenterVertically) {
                    DominantHand.values().forEach { hand ->
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier
                                .clickable { selectedHand = hand }
                                .padding(end = 8.dp)
                        ) {
                            RadioButton(
                                selected = selectedHand == hand,
                                onClick = { selectedHand = hand }
                            )
                            Text(hand.displayName, style = MaterialTheme.typography.bodySmall)
                        }
                    }
                }

                Spacer(modifier = Modifier.height(8.dp))

                Text("Estado del jugador:", style = MaterialTheme.typography.bodyMedium)
                Row(verticalAlignment = Alignment.CenterVertically) {
                    PlayerStatus.values().forEach { status ->
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier
                                .clickable { selectedStatus = status }
                                .padding(end = 8.dp)
                        ) {
                            RadioButton(
                                selected = selectedStatus == status,
                                onClick = { selectedStatus = status }
                            )
                            Text(status.displayName, style = MaterialTheme.typography.bodySmall)
                        }
                    }
                }

                Spacer(modifier = Modifier.height(8.dp))

                OutlinedTextField(
                    value = notes,
                    onValueChange = { notes = it },
                    label = { Text("Notas / Observaciones") },
                    modifier = Modifier.fillMaxWidth()
                )
            }
        },
        confirmButton = {
            TextButton(
                onClick = {
                    val jersey = jerseyNumberStr.toIntOrNull() ?: 0
                    val height = heightCmStr.toDoubleOrNull() ?: 0.0
                    val player = Player(
                        id = playerToEdit?.id ?: 0L,
                        teamId = teamId,
                        name = name.trim(),
                        jerseyNumber = jersey,
                        position = selectedPosition,
                        heightCm = height,
                        dominantHand = selectedHand,
                        notes = notes.trim(),
                        status = selectedStatus
                    )
                    onSave(player)
                },
                enabled = name.isNotBlank() && jerseyNumberStr.isNotBlank()
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