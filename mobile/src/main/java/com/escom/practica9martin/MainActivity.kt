package com.escom.practica9martin

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.escom.practica9martin.data.PreferencesManager
import com.escom.practica9martin.data.WaterRepository

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val preferencesManager = PreferencesManager(applicationContext)
        val repository = WaterRepository(preferencesManager)
        val viewModel = WaterViewModel(repository)

        setContent {
            MaterialTheme {
                WaterScreen(viewModel)
            }
        }
    }
}

@Composable
fun WaterScreen(viewModel: WaterViewModel) {
    val waterCount by viewModel.waterCount.collectAsState()
    val dailyGoal by viewModel.dailyGoal.collectAsState()
    val progress = if (dailyGoal > 0) (waterCount.toFloat() / dailyGoal) else 0f

    var showGoalDialog by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF1E1E1E))
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        // Header
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(top = 40.dp)
        ) {
            Text(
                text = "💧 HydroWatch",
                fontSize = 32.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF03A9F4)
            )

            Text(
                text = "Tu compañero de hidratación",
                fontSize = 14.sp,
                color = Color.Gray,
                modifier = Modifier.padding(top = 4.dp)
            )
        }

        // Progress Circle
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier.size(250.dp)
        ) {
            CircularProgressIndicator(
                progress = progress,
                modifier = Modifier.fillMaxSize(),
                strokeWidth = 16.dp,
                color = Color(0xFF03A9F4),
                trackColor = Color(0xFF37474F)
            )

            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "$waterCount",
                    fontSize = 72.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
                Text(
                    text = "de $dailyGoal vasos",
                    fontSize = 18.sp,
                    color = Color.Gray
                )

                if (waterCount >= dailyGoal) {
                    Text(
                        text = "🎉 ¡Meta alcanzada!",
                        fontSize = 16.sp,
                        color = Color(0xFF4CAF50),
                        modifier = Modifier.padding(top = 8.dp)
                    )
                }
            }
        }

        // Buttons
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(bottom = 40.dp)
        ) {
            Button(
                onClick = { viewModel.addWater() },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(60.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF03A9F4)
                ),
                shape = RoundedCornerShape(16.dp)
            ) {
                Text(
                    text = "+ Beber Agua",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )
            }

            Spacer(modifier = Modifier.height(12.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                OutlinedButton(
                    onClick = { viewModel.removeWater() },
                    modifier = Modifier
                        .weight(1f)
                        .height(50.dp),
                    colors = ButtonDefaults.outlinedButtonColors(
                        contentColor = Color(0xFFFF5252)
                    ),
                    shape = RoundedCornerShape(12.dp)
                ) {
                    Text("- Quitar")
                }

                OutlinedButton(
                    onClick = { viewModel.reset() },
                    modifier = Modifier
                        .weight(1f)
                        .height(50.dp),
                    colors = ButtonDefaults.outlinedButtonColors(
                        contentColor = Color.Gray
                    ),
                    shape = RoundedCornerShape(12.dp)
                ) {
                    Text("Reiniciar")
                }
            }

            Spacer(modifier = Modifier.height(12.dp))

            TextButton(
                onClick = { showGoalDialog = true }
            ) {
                Text(
                    text = "Cambiar meta diaria",
                    color = Color(0xFF03A9F4)
                )
            }
        }
    }

    if (showGoalDialog) {
        GoalDialog(
            currentGoal = dailyGoal,
            onDismiss = { showGoalDialog = false },
            onConfirm = { newGoal ->
                viewModel.setGoal(newGoal)
                showGoalDialog = false
            }
        )
    }
}

@Composable
fun GoalDialog(
    currentGoal: Int,
    onDismiss: () -> Unit,
    onConfirm: (Int) -> Unit
) {
    var goalText by remember { mutableStateOf(currentGoal.toString()) }

    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("Meta diaria") },
        text = {
            Column {
                Text("¿Cuántos vasos de agua quieres beber al día?")
                Spacer(modifier = Modifier.height(16.dp))
                OutlinedTextField(
                    value = goalText,
                    onValueChange = { goalText = it.filter { char -> char.isDigit() } },
                    label = { Text("Vasos") },
                    singleLine = true
                )
            }
        },
        confirmButton = {
            TextButton(
                onClick = {
                    val goal = goalText.toIntOrNull() ?: 8
                    onConfirm(goal.coerceIn(1, 20))
                }
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