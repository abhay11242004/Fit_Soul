package com.example.fit_soul.data

import android.content.Context
import android.util.Log
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "profile_datastore")


class ProfileDataStore(private val context: Context) {

    companion object {
        private val HEART_RATE_KEY = intPreferencesKey("heart_rate")
        private val HEART_RATE_HISTORY_KEY = stringPreferencesKey("heart_rate_history")
    }

    suspend fun saveHeartRate(heartRate: Int) {
        context.dataStore.edit { preferences ->
            preferences[HEART_RATE_KEY] = heartRate
            val currentList = preferences[HEART_RATE_HISTORY_KEY]?.split(",")?.map { it.toInt() } ?: emptyList()
            val updatedList = currentList + heartRate
            preferences[HEART_RATE_HISTORY_KEY] = updatedList.joinToString(",")
        }
    }

    val heartRateList: Flow<List<Int>> = context.dataStore.data.map { preferences ->
        val storedList = preferences[HEART_RATE_HISTORY_KEY] ?: ""
        if (storedList.isNotEmpty()) {
            storedList.split(",").map { it.toInt() }
        } else {
            emptyList()
        }
    }
}
