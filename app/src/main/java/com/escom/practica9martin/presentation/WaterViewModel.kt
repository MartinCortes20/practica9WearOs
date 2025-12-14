package com.escom.practica9martin.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.escom.practica9martin.data.WaterRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class WaterViewModel(private val repository: WaterRepository) : ViewModel() {

    val waterCount: StateFlow<Int> = repository.waterCount.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = 0
    )

    val dailyGoal: StateFlow<Int> = repository.dailyGoal.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = 8
    )

    fun addWater() {
        viewModelScope.launch {
            repository.addWater()
        }
    }

    fun reset() {
        viewModelScope.launch {
            repository.reset()
        }
    }
}