package com.escom.practica9martin.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.wear.compose.material.*
// Cambia estos imports:
import androidx.wear.compose.foundation.lazy.ScalingLazyColumn
import androidx.wear.compose.foundation.lazy.AutoCenteringParams

@Composable
fun WaterScreen(viewModel: WaterViewModel) {
    val waterCount by viewModel.waterCount.collectAsState()
    val dailyGoal by viewModel.dailyGoal.collectAsState()
    val progress = if (dailyGoal > 0) {
        (waterCount.toFloat() / dailyGoal.toFloat()).coerceIn(0f, 1f)
    } else {
        0f
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colors.background),
        contentAlignment = Alignment.Center
    ) {
        ScalingLazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(
                top = 30.dp,
                bottom = 30.dp,
                start = 10.dp,
                end = 10.dp
            ),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            autoCentering = AutoCenteringParams(itemIndex = 0)
        ) {
            item {
                Text(
                    text = "💧 Hidratación",
                    style = MaterialTheme.typography.title2,
                    textAlign = TextAlign.Center,
                    color = Color(0xFF03A9F4)
                )
            }

            item { Spacer(modifier = Modifier.height(12.dp)) }

            item {
                CircularProgressIndicator(
                    progress = progress,
                    modifier = Modifier.size(100.dp),
                    strokeWidth = 6.dp,
                    indicatorColor = Color(0xFF03A9F4),
                    trackColor = Color(0xFF37474F)
                )
            }

            item { Spacer(modifier = Modifier.height(12.dp)) }

            item {
                Text(
                    text = "$waterCount / $dailyGoal",
                    style = MaterialTheme.typography.display1,
                    color = Color.White
                )
            }

            item {
                Text(
                    text = "vasos de agua",
                    style = MaterialTheme.typography.caption1,
                    color = Color(0xFFB0BEC5)
                )
            }

            item { Spacer(modifier = Modifier.height(16.dp)) }

            item {
                Button(
                    onClick = { viewModel.addWater() },
                    modifier = Modifier
                        .fillMaxWidth(0.8f)
                        .height(50.dp),
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = Color(0xFF03A9F4),
                        contentColor = Color.White
                    )
                ) {
                    Text(
                        text = "+ Beber Agua",
                        style = MaterialTheme.typography.button
                    )
                }
            }

            item { Spacer(modifier = Modifier.height(8.dp)) }

            item {
                CompactButton(
                    onClick = { viewModel.reset() },
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = Color(0xFF546E7A),
                        contentColor = Color.White
                    )
                ) {
                    Text("Reiniciar Día")
                }
            }

            item { Spacer(modifier = Modifier.height(8.dp)) }

            item {
                Text(
                    text = if (waterCount >= dailyGoal) {
                        "🎉 ¡Meta alcanzada!"
                    } else {
                        "Sigue así 💪"
                    },
                    style = MaterialTheme.typography.caption1,
                    color = if (waterCount >= dailyGoal) Color(0xFF4CAF50) else Color(0xFFB0BEC5),
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}