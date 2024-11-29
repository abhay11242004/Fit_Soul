package com.example.fit_soul.ui.heartRate

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.fit_soul.data.ProfileData
import java.text.SimpleDateFormat
import java.util.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HeartRateScreen(viewModel: ProfileData) {
    val currentHeartRate by viewModel.currentHeartRate.collectAsState()
    val heartRateHistory by viewModel.heartRateHistory.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Heart Rate Monitor") })
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Spacer(modifier = Modifier.height(16.dp))
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
                contentAlignment = Alignment.Center
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    if (currentHeartRate != null) {
                        Text(
                            text = "${currentHeartRate} BPM",
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.padding(bottom = 16.dp)
                        )
                    }
                    Button(onClick = {
                        viewModel.measureHeartRate()
                    }) {
                        Text("Measure Heart Rate")
                    }
                }
            }

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .padding(horizontal = 16.dp),
            ) {
                if (heartRateHistory.isEmpty()) {
                    Text(
                        text = "No heart rate data available.",
                        modifier = Modifier.align(Alignment.Center)
                    )
                } else {
                    LazyColumn {
                        items(heartRateHistory.size) { index ->
                            val (rate, timestamp) = heartRateHistory[index]
                            HeartRateHistoryItem(rate = rate, timestamp = timestamp)
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun HeartRateHistoryItem(rate: Int, timestamp: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(text = "$rate BPM")
        Text(text = timestamp)
    }
}



