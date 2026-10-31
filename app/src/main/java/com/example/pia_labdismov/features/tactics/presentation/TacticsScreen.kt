package com.example.pia_labdismov.features.tactics.presentation

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Brush
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Gesture
import androidx.compose.material.icons.filled.SportsVolleyball
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.pia_labdismov.features.tactics.domain.model.TacticalToken
import com.example.pia_labdismov.features.tactics.domain.model.TokenType

data class LinePath(val points: MutableList<Offset> = mutableListOf(), val color: Color = Color.Yellow)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TacticsScreen() {
    var toolMode by remember { mutableStateOf(ToolMode.MOVE_TOKENS) }

    // Initial tactical tokens on a volleyball court (half court or full court)
    val tokens = remember {
        mutableStateListOf(
            TacticalToken("1", TokenType.PLAYER_HOME, "1", 0.5f, 0.7f, "#0D47A1"),
            TacticalToken("2", TokenType.PLAYER_HOME, "2", 0.2f, 0.8f, "#0D47A1"),
            TacticalToken("3", TokenType.PLAYER_HOME, "3", 0.8f, 0.8f, "#0D47A1"),
            TacticalToken("4", TokenType.PLAYER_HOME, "4", 0.2f, 0.6f, "#0D47A1"),
            TacticalToken("5", TokenType.PLAYER_HOME, "5", 0.5f, 0.9f, "#0D47A1"),
            TacticalToken("6", TokenType.PLAYER_HOME, "6", 0.8f, 0.6f, "#0D47A1"),
            TacticalToken("ball", TokenType.BALL, "Ball", 0.5f, 0.5f, "#FBC02D")
        )
    }

    val drawingPaths = remember { mutableStateListOf<LinePath>() }
    var currentPath by remember { mutableStateOf<LinePath?>(null) }
    var selectedTokenId by remember { mutableStateOf<String?>(null) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Pizarra Táctica 9x18m", fontWeight = FontWeight.Bold) },
                actions = {
                    IconButton(onClick = { drawingPaths.clear() }) {
                        Icon(Icons.Default.Clear, contentDescription = "Limpiar trazos")
                    }
                },
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
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Tool selector
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                Button(
                    onClick = { toolMode = ToolMode.MOVE_TOKENS },
                    enabled = toolMode != ToolMode.MOVE_TOKENS
                ) {
                    Icon(Icons.Default.SportsVolleyball, contentDescription = null)
                    Spacer(modifier = Modifier.padding(horizontal = 4.dp))
                    Text("Mover Fichas")
                }

                Button(
                    onClick = { toolMode = ToolMode.DRAW_LINES },
                    enabled = toolMode != ToolMode.DRAW_LINES
                ) {
                    Icon(Icons.Default.Brush, contentDescription = null)
                    Spacer(modifier = Modifier.padding(horizontal = 4.dp))
                    Text("Dibujar Trazos")
                }
            }

            Spacer(modifier = Modifier.height(12.dp))

            // Volleyball Court Canvas
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .clip(RoundedCornerShape(12.dp))
                    .background(Color(0xFF1E88E5)) // Court Blue
                    .pointerInput(toolMode) {
                        if (toolMode == ToolMode.DRAW_LINES) {
                            detectDragGestures(
                                onDragStart = { offset ->
                                    currentPath = LinePath(mutableListOf(offset), Color.Yellow)
                                    currentPath?.let { drawingPaths.add(it) }
                                },
                                onDrag = { change, _ ->
                                    currentPath?.points?.add(change.position)
                                },
                                onDragEnd = {
                                    currentPath = null
                                }
                            )
                        }
                    }
            ) {
                Canvas(modifier = Modifier.fillMaxSize()) {
                    val width = size.width
                    val height = size.height

                    // Draw Court Lines (White)
                    val strokeWidth = 4.dp.toPx()
                    val lineColor = Color.White

                    // Outer boundary (9m x 18m)
                    drawRect(color = lineColor, size = size, style = Stroke(width = strokeWidth))

                    // Net line in the middle
                    drawLine(
                        color = Color.Black,
                        start = Offset(0f, height / 2),
                        end = Offset(width, height / 2),
                        strokeWidth = strokeWidth * 1.5f
                    )

                    // Attack lines (3m from net)
                    val attackOffset = height * 0.33f
                    drawLine(
                        color = lineColor,
                        start = Offset(0f, height / 2 - attackOffset),
                        end = Offset(width, height / 2 - attackOffset),
                        strokeWidth = strokeWidth,
                        pathEffect = androidx.compose.ui.graphics.PathEffect.dashPathEffect(floatArrayOf(20f, 20f))
                    )
                    drawLine(
                        color = lineColor,
                        start = Offset(0f, height / 2 + attackOffset),
                        end = Offset(width, height / 2 + attackOffset),
                        strokeWidth = strokeWidth,
                        pathEffect = androidx.compose.ui.graphics.PathEffect.dashPathEffect(floatArrayOf(20f, 20f))
                    )

                    // Draw paths
                    for (linePath in drawingPaths) {
                        if (linePath.points.size > 1) {
                            val path = Path().apply {
                                moveTo(linePath.points[0].x, linePath.points[0].y)
                                for (i in 1 until linePath.points.size) {
                                    lineTo(linePath.points[i].x, linePath.points[i].y)
                                }
                            }
                            drawPath(path = path, color = linePath.color, style = Stroke(width = 6f))
                        }
                    }
                }

                // Render tokens as draggable UI elements
                tokens.forEach { token ->
                    val tokenColor = try {
                        Color(android.graphics.Color.parseColor(token.colorHex))
                    } catch (e: Exception) {
                        Color.DarkGray
                    }

                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .pointerInput(token.id, toolMode) {
                                if (toolMode == ToolMode.MOVE_TOKENS) {
                                    detectDragGestures(
                                        onDrag = { change, dragAmount ->
                                            val canvasWidth = size.width.toFloat()
                                            val canvasHeight = size.height.toFloat()
                                            if (canvasWidth > 0 && canvasHeight > 0) {
                                                val newX = (token.x * canvasWidth + dragAmount.x).coerceIn(40f, canvasWidth - 40f) / canvasWidth
                                                val newY = (token.y * canvasHeight + dragAmount.y).coerceIn(40f, canvasHeight - 40f) / canvasHeight
                                                val index = tokens.indexOfFirst { it.id == token.id }
                                                if (index != -1) {
                                                    tokens[index] = token.copy(x = newX, y = newY)
                                                }
                                            }
                                        }
                                    )
                                }
                            },
                        contentAlignment = Alignment.TopStart
                    ) {
                        // Position token based on normalized coordinates
                        Box(
                            modifier = Modifier
                                .padding(start = 0.dp, top = 0.dp)
                                .size(44.dp)
                                .clip(CircleShape)
                                .background(tokenColor)
                                .align(Alignment.Center),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = token.label,
                                color = Color.White,
                                fontWeight = FontWeight.Bold,
                                style = MaterialTheme.typography.bodyMedium
                            )
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "Modo activo: ${if (toolMode == ToolMode.MOVE_TOKENS) "Arrastra las fichas de los jugadores por la cancha" else "Dibuja trazos tácticos a mano alzada"}",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}

enum class ToolMode {
    MOVE_TOKENS,
    DRAW_LINES
}