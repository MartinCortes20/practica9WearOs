package com.escom.practica9martin.data

import com.google.firebase.database.FirebaseDatabase
import kotlinx.coroutines.flow.Flow

class WaterRepository(private val preferencesManager: PreferencesManager) {

    private val database = FirebaseDatabase.getInstance()
    private val waterRef = database.getReference("water_intake")

    val waterCount: Flow<Int> = preferencesManager.waterCount
    val dailyGoal: Flow<Int> = preferencesManager.dailyGoal

    suspend fun addWater() {
        preferencesManager.incrementWater()
        syncToFirebase()
    }

    suspend fun removeWater() {
        preferencesManager.decrementWater()
    }

    suspend fun reset() {
        preferencesManager.resetWater()
    }

    suspend fun setGoal(goal: Int) {
        preferencesManager.setGoal(goal)
    }

    private fun syncToFirebase() {
        val userId = "user_mobile"
        val timestamp = System.currentTimeMillis()

        waterRef.child(userId).child(timestamp.toString()).setValue(
            mapOf(
                "timestamp" to timestamp,
                "glasses" to 1,
                "device" to "mobile"
            )
        )
    }
}