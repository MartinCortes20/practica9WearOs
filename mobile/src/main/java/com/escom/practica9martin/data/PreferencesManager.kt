package com.escom.practica9martin.data

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "water_prefs")

class PreferencesManager(private val context: Context) {

    companion object {
        private val WATER_COUNT_KEY = intPreferencesKey("water_count")
        private val DAILY_GOAL_KEY = intPreferencesKey("daily_goal")
    }

    val waterCount: Flow<Int> = context.dataStore.data.map { preferences ->
        preferences[WATER_COUNT_KEY] ?: 0
    }

    val dailyGoal: Flow<Int> = context.dataStore.data.map { preferences ->
        preferences[DAILY_GOAL_KEY] ?: 8
    }

    suspend fun incrementWater() {
        context.dataStore.edit { preferences ->
            val current = preferences[WATER_COUNT_KEY] ?: 0
            preferences[WATER_COUNT_KEY] = current + 1
        }
    }

    suspend fun decrementWater() {
        context.dataStore.edit { preferences ->
            val current = preferences[WATER_COUNT_KEY] ?: 0
            if (current > 0) {
                preferences[WATER_COUNT_KEY] = current - 1
            }
        }
    }

    suspend fun resetWater() {
        context.dataStore.edit { preferences ->
            preferences[WATER_COUNT_KEY] = 0
        }
    }

    suspend fun setGoal(goal: Int) {
        context.dataStore.edit { preferences ->
            preferences[DAILY_GOAL_KEY] = goal
        }
    }
}