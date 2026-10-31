package com.example.pia_labdismov.features.teams.presentation.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
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
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun CsvImportExportDialog(
    initialCsvText: String?,
    onDismiss: () -> Unit,
    onImport: (String) -> Unit
) {
    var csvInputText by remember { mutableStateOf(initialCsvText ?: "Nombre,Numero,Posicion,Altura,Mano,Notas,Estado\nJuan Perez,7,SETTER,188,RIGHT,Capitán,ACTIVE\n") }

    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("Importar / Exportar Plantilla CSV") },
        text = {
            Column(modifier = Modifier.fillMaxWidth()) {
                Text(
                    text = "Copia el contenido CSV generado o pega las líneas en formato:\nNombre, Numero, Posicion, Altura, Mano, Notas, Estado",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
                Spacer(modifier = Modifier.height(8.dp))

                OutlinedTextField(
                    value = csvInputText,
                    onValueChange = { csvInputText = it },
                    modifier = Modifier
                        .fillMaxWidth()
                        .heightIn(min = 150.dp, max = 250.dp)
                        .verticalScroll(rememberScrollState()),
                    label = { Text("Contenido CSV") }
                )
            }
        },
        confirmButton = {
            TextButton(
                onClick = { onImport(csvInputText) },
                enabled = csvInputText.isNotBlank()
            ) {
                Text("Importar CSV")
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text("Cerrar")
            }
        }
    )
}