package com.example.fit_soul.data

import android.util.Log
import androidx.collection.MutableIntList
import androidx.collection.mutableIntListOf
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class ProfileData(private val profileDataStore: ProfileDataStore) : ViewModel() {

    private var _currentHeartRate = mutableStateOf(0)
    val currentHeartRate: MutableState<Int> get() = _currentHeartRate

    private var _heartRateHistory = mutableStateOf<List<Int>>(emptyList())
    val heartRateHistory: MutableState<List<Int>> get() = _heartRateHistory

    init {
        viewModelScope.launch {
            profileDataStore.heartRateList.collectLatest { list ->
                _heartRateHistory.value = list
            }
//            if (list.isNotEmpty()) {
//                _currentHeartRate.value = list.last() // Set the most recent heart rate
//            }
        }
    }

    fun measureHeartRate() {
//        val randomRate = (60..120).random()
//        val timestamp = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault()).format(Date())
//
//        viewModelScope.launch {
//            _currentHeartRate.value = randomRate
//            _heartRateHistory.value = _heartRateHistory.value + (randomRate to timestamp)
//        }
        val randomRate = (60..120).random()
        viewModelScope.launch {
            profileDataStore.saveHeartRate(randomRate)
            _currentHeartRate.value = randomRate
        }
    }
}