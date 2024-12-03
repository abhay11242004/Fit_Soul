package com.example.fit_soul.data

import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fit_soul.ui.chatBox.MessageModel
import com.google.ai.client.generativeai.GenerativeModel
import kotlinx.coroutines.launch

class ChatViewModel(): ViewModel() {
    val messageList by lazy {
        mutableStateListOf<MessageModel>()
    }
    val generativeModel: GenerativeModel = GenerativeModel(
        modelName = "gemini-pro",
        apiKey = "AIzaSyCVOERvPIYg7DxhGPceGptjUXp8cgjua28"
    )
    fun sendMessage(question : String){
        viewModelScope.launch {
            try {
                val chat = generativeModel.startChat()

                messageList.add(MessageModel(question, "user"))

                val response = chat.sendMessage(question)
                messageList.add(MessageModel(response.text.toString(), "model"))

            } catch (e: Exception) {
                Log.e("ChatViewModel", "Error sending message: ${e.localizedMessage}", e)
            }
        }
    }
}