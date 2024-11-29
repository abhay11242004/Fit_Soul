package com.example.fit_soul.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.fit_soul.data.ProfileData
import com.example.fit_soul.ui.chatBox.ChatScreen
import com.example.fit_soul.ui.heartRate.HeartRateScreen
import com.example.fit_soul.ui.home.HomeScreen
import com.example.fit_soul.ui.weightTracker.WeightTrackingScreen

@Composable
fun NavigationHost(navController: NavHostController){
    var viewModel = ProfileData()
    NavHost(navController = navController, startDestination = "Home") {
        composable("Home") { HomeScreen() }
        composable("ChatBot") { ChatScreen() }
        composable("HeartRate") { HeartRateScreen(viewModel) }
        composable("Weight") {  }
    }
}