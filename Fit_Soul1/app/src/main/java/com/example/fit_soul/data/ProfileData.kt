package com.example.fit_soul.data

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.quizapp.ProfileDataStore
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class ProfileData(private val profileDataStore: ProfileDataStore) : ViewModel() {
    private val _currentHeartRate = MutableStateFlow<Int?>(null)
    val currentHeartRate: StateFlow<Int?> get() = _currentHeartRate


//    private val _heartRateHistory = MutableStateFlow<List<Pair<Int, String>>>(emptyList())
//    val heartRateHistory: StateFlow<List<Pair<Int, String>>> get() = _heartRateHistory

    init {
        viewModelScope.launch {
            profileDataStore.heartRate.collectLatest { heartRate ->
                heartRate.value = heartRate
            }
        }
    }
    fun measureHeartRate() {
        val randomRate = (60..120).random()
        val timestamp = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault()).format(Date())
    }

//    {
//        viewModelScope.launch {
//            _currentHeartRate.value = randomRate
//            _heartRateHistory.value = _heartRateHistory.value + (randomRate to timestamp)
//        }
//    }
    fun SaveHeartRate(hRate: Int) {
    _currentHeartRate.value = hRate
        viewModelScope.launch {
            profileDataStore.saveHeartRate(_currentHeartRate.value)
        }
    }

}
