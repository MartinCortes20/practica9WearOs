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

    suspend fun reset() {
        preferencesManager.resetWater()
    }

    private fun syncToFirebase() {
        val userId = "user_wear"
        val timestamp = System.currentTimeMillis()

        waterRef.child(userId).child(timestamp.toString()).setValue(
            mapOf(
                "timestamp" to timestamp,
                "glasses" to 1
            )
        )
    }
}