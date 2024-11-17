package com.example.fit_soul

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.fit_soul.ui.theme.Fit_SoulTheme
import com.example.fit_soul.ui.heartRate.heartRateScreen
import com.example.fit_soul.ui.chatBox.chatBoxScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Fit_SoulTheme {
//                ChatScreen()
                HeartRateScreen()
            }
        }
    }
}
