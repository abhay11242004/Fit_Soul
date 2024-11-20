package com.example.fit_soul.ui.chatBox

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import retrofit2.*
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

@Composable
fun ChatScreen() {
    var inputText by remember { mutableStateOf("") }
    val messages = remember { mutableStateListOf("Bot: Hello! How can I assist you today?") }

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        LazyColumn(modifier = Modifier.weight(1f)) {
            items(messages) { message ->
                Text(text = message, modifier = Modifier.padding(8.dp))
            }
        }

        Row {
            TextField(
                value = inputText,
                onValueChange = { inputText = it },
                modifier = Modifier.weight(1f),
                label = { Text("Type your message") }
            )
            Button(onClick = {
                if (inputText.isNotBlank()) {
                    messages.add("You: $inputText")
                    getChatResponse(inputText) { response ->
                        messages.add("Bot: $response")
                    }
                    inputText = ""
                }
            }) {
                Text("Send")
            }
        }
    }
}

interface BrainShopService {
    @GET("get")
    fun getChatResponse(
        @Query("bid") bid: String,
        @Query("key") key: String,
        @Query("uid") uid: String,
        @Query("msg") msg: String
    ): Call<BrainShopResponse>
}

data class BrainShopResponse(val cnt: String)

fun getChatResponse(message: String, onResponse: (String) -> Unit) {
    val retrofit = Retrofit.Builder()
        .baseUrl("http://api.brainshop.ai/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val service = retrofit.create(BrainShopService::class.java)

    val bid = "" // Replace with your actual brain ID
    val key = "" // Replace with your actual API key
    val uid = "" // Unique user ID; can be any string

    service.getChatResponse(bid, key, uid, message).enqueue(object : Callback<BrainShopResponse> {
        override fun onResponse(
            call: Call<BrainShopResponse>,
            response: Response<BrainShopResponse>
        ) {
            if (response.isSuccessful) {
                val responseBody = response.body()
                val botResponse = responseBody?.cnt ?: "No response"
                onResponse(botResponse)
            } else {
                onResponse("Error: ${response.code()}")
            }
        }

        override fun onFailure(call: Call<BrainShopResponse>, t: Throwable) {
            onResponse("Network error: ${t.localizedMessage}")
        }
    })
}
