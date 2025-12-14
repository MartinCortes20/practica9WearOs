package com.escom.practica9martin.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.wear.compose.material.MaterialTheme
import com.escom.practica9martin.data.PreferencesManager
import com.escom.practica9martin.data.WaterRepository
import com.escom.practica9martin.presentation.theme.Practica9MartinTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Inicializar dependencias
        val preferencesManager = PreferencesManager(applicationContext)
        val repository = WaterRepository(preferencesManager)
        val viewModel = WaterViewModel(repository)

        setContent {
            WearApp(viewModel)
        }
    }
}

@Composable
fun WearApp(viewModel: WaterViewModel) {
    Practica9MartinTheme {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colors.background)
        ) {
            WaterScreen(viewModel = viewModel)
        }
    }
}