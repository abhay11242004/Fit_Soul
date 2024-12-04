package com.example.fit_soul.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.fit_soul.data.ChatViewModel
import com.example.fit_soul.data.ProfileData
import com.example.fit_soul.ui.chatBox.ChatScreen
import com.example.fit_soul.ui.heartRate.HeartRateScreen
import com.example.fit_soul.ui.home.HomeScreen
import com.example.fit_soul.ui.weightTracker.WeightTrackingScreen

@Composable
fun NavigationHost(navController: NavHostController, modifier: Modifier){
    var viewModel = ProfileData()
    var chatViewModel = ChatViewModel()
    NavHost(navController = navController, startDestination = "Home") {
        composable("Home") { HomeScreen(viewModel) }
        composable("ChatBot") { ChatScreen(viewModel = chatViewModel, modifier = modifier) }
        composable("HeartRate") { HeartRateScreen(viewModel) }
        composable("Weight") { WeightTrackingScreen(viewModel) }
    }
}