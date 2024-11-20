package com.example.fit_soul.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.fit_soul.ui.chatBox.ChatScreen
import com.example.fit_soul.ui.heartRate.HeartRateScreen
import com.example.fit_soul.ui.home.HomeScreen

@Composable
fun NavigationHost(navController: NavHostController){
    NavHost(navController = navController, startDestination = "Home") {
        composable("Home") { HomeScreen() }
        composable("ChatBot") { ChatScreen() }
        composable("HeartRate") { HeartRateScreen() }
        composable("Weight") { HomeScreen() }
    }
}