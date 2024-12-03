package com.example.fit_soul.data

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.ai.client.generativeai.GenerativeModel
import kotlinx.coroutines.launch

class ChatViewModel(): ViewModel() {
    val generativeModel: GenerativeModel = GenerativeModel(
        modelName = "gemini-pro",
        apiKey = "AIzaSyDZYaVuTTJOOA2fueRHOKEIDz3VJO5_Dz4"
    )
    fun sendMessage(question : String){
        viewModelScope.launch {
            val chat = generativeModel.startChat()
        }
    }
}