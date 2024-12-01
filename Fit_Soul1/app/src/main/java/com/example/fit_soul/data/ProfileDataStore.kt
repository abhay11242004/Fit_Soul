package com.example.quizapp

import android.content.Context
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map


private val Context.dataStore by preferencesDataStore(name = "profile_datastore")
class ProfileDataStore(private val context: Context) {
    companion object {
        private val HEART_RATE_KEY = intPreferencesKey("heart_rate")
    }

    val heartRate: Flow<Int> = context.dataStore.data
        .map {
            it[HEART_RATE_KEY] ?: 0
        }

    suspend fun saveHeartRate(heartRate: Int) {
        context.dataStore.edit {
            it[HEART_RATE_KEY] = heartRate
        }
    }
}
