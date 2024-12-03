package com.example.fit_soul.ui.chatBox

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Send
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.fit_soul.data.ChatViewModel


@Composable
fun ChatScreen(modifier: Modifier = Modifier, viewModel: ChatViewModel) {
    Column(
        modifier = modifier
    ){
        AppHeader()
        MessageList(messageList = viewModel.messageList)
        MessageInput(onMessageSend = {
            viewModel.sendMessage(it)
        })
    }

}
@Composable
fun MessageList(modifier: Modifier = Modifier, messageList: List<MessageModel>){
    LazyColumn {
        items(messageList){
            Text(text = it.message)
        }

    }
}

@Composable
fun AppHeader(){
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.primary)
    ){
        Text(
            modifier = Modifier.padding(16.dp),
            text = "ChatGpt 300",
            color = Color.White,
            fontSize = 22.sp
        )
    }
}

@Composable
fun MessageInput(onMessageSend: (String) -> Unit){

    var message by remember {
        mutableStateOf("")
    }

    Row(
        modifier = Modifier.padding(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ){
        OutlinedTextField(
            modifier = Modifier.weight(1f),
            value = message,
            onValueChange = {
                message = it
            }
        )
        IconButton(onClick = {
            onMessageSend(message)
            message = ""
        }){
            Icon(imageVector = Icons.Default.Send,
                contentDescription = "Send" )
        }
    }

}


//interface BrainShopService {
//    @GET("get")
//    fun getChatResponse(
//        @Query("bid") bid: String,
//        @Query("key") key: String,
//        @Query("uid") uid: String,
//        @Query("msg") msg: String
//    ): Call<BrainShopResponse>
//}
//
//data class BrainShopResponse(val cnt: String)
//
//fun getChatResponse(message: String, onResponse: (String) -> Unit) {
//    val retrofit = Retrofit.Builder()
//        .baseUrl("http://api.brainshop.ai/")
//        .addConverterFactory(GsonConverterFactory.create())
//        .build()
//
//    val service = retrofit.create(BrainShopService::class.java)
//
//    val bid = "183777" // Replace with your actual brain ID
//    val key = "9PKSvpdRC30JaPjF" // Replace with your actual API key
//    val uid = "arsalan123" // Unique user ID; can be any string
//
//    service.getChatResponse(bid, key, uid, message).enqueue(object : Callback<BrainShopResponse> {
//        override fun onResponse(
//            call: Call<BrainShopResponse>,
//            response: Response<BrainShopResponse>
//        ) {
//            if (response.isSuccessful) {
//                val responseBody = response.body()
//                val botResponse = responseBody?.cnt ?: "No response"
//                onResponse(botResponse)
//            } else {
//                onResponse("Error: ${response.code()}")
//            }
//        }
//
//        override fun onFailure(call: Call<BrainShopResponse>, t: Throwable) {
//            onResponse("Network error: ${t.localizedMessage}")
//        }
//    })
//}
