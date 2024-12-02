package com.example.fit_soul.data

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fit_soul.R
import com.example.quizapp.ProfileDataStore
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class ProfileData() : ViewModel() {
    private val _currentHeartRate = MutableStateFlow<Int?>(null)
    val currentHeartRate: StateFlow<Int?> get() = _currentHeartRate


    private val _heartRateHistory = MutableStateFlow<List<Pair<Int, String>>>(emptyList())
    val heartRateHistory: StateFlow<List<Pair<Int, String>>> get() = _heartRateHistory
     val images = listOf(
        R.drawable.download,
        R.drawable.download1,
    )
    val texts = listOf(
        "I train, eat, sleep, and repeat.",
        "Power is not in muscles, it comes from God",
    )
    private var _currentIndex by mutableStateOf(0)
    val currentIndex: Int get() = _currentIndex

    var currentWeight by mutableStateOf("")
    val startWeight: String get() = "70"
    val targetWeight: String get() = "65"

    fun onCurrentWeightChange(newWeight: String) {
        currentWeight = newWeight
    }
    fun nextItem() {
        _currentIndex = (_currentIndex + 1) % images.size
    }
    /*init {
        viewModelScope.launch {
            profileDataStore.heartRate.collectLatest { heartRate ->
                heartRate.value = heartRate
            }
        }
    }*/
    fun measureHeartRate() {
        val randomRate = (60..120).random()
        val timestamp = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault()).format(Date())

        viewModelScope.launch {
            _currentHeartRate.value = randomRate
            _heartRateHistory.value = _heartRateHistory.value + (randomRate to timestamp)
        }
    }
    /*fun SaveHeartRate(hRate: Int) {
    _currentHeartRate.value = hRate
        viewModelScope.launch {
            profileDataStore.saveHeartRate(_currentHeartRate.value)
        }
    }*/

}
