package com.example.fit_soul.ui.chatBox

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Send
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.fit_soul.data.ChatViewModel
import com.example.fit_soul.ui.theme.ColoModelMessage
import com.example.fit_soul.ui.theme.ColorUserMessage


@Composable
fun ChatScreen(modifier: Modifier = Modifier, viewModel: ChatViewModel) {
    Column(
        modifier = modifier
            .fillMaxSize()

    ) {
        AppHeader()
        MessageList(
            modifier = Modifier
                .weight(1f) // Take up available vertical space
                .padding(horizontal = 16.dp),
            messageList = viewModel.messageList
        )
        MessageInput(
            onMessageSend = {
                viewModel.sendMessage(it)
            },
            modifier = Modifier
                .navigationBarsPadding() // Ensure it's above the navigation bar
                .padding(10.dp)
        )
    }

}
@Composable
fun MessageList(modifier: Modifier = Modifier, messageList: List<MessageModel>){
    LazyColumn(
        modifier = modifier,
        reverseLayout = true
    ) {
        items(messageList.reversed()){
            MessageRow(messageModel = it)
        }

    }
}
@Composable
fun MessageRow(messageModel: MessageModel){
    val isModel = messageModel.role == "model"

    Row(
        verticalAlignment = Alignment.CenterVertically
    ){
        Box(
            modifier = Modifier.fillMaxWidth()
        ){
            Box(
                modifier = Modifier.align(if(isModel) Alignment.BottomStart else Alignment.BottomEnd)
                .padding(
                    start = if(isModel) 8.dp else 70.dp,
                    end = if(isModel) 70.dp else 8.dp,
                    top = 8.dp,
                    bottom = 8.dp
                )
                .clip(RoundedCornerShape(48f))
                .background(if(isModel) ColoModelMessage else ColorUserMessage)
                .padding(16.dp)

            ){
                Text(
                    text = messageModel.message,
                    fontWeight = FontWeight.W500)
            }

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
            text = "Gemini",
            color = Color.White,
            fontSize = 22.sp
        )
    }
}

@Composable
fun MessageInput(onMessageSend: (String) -> Unit,modifier: Modifier = Modifier){

    var message by remember {
        mutableStateOf("")
    }
    Row(
        modifier = modifier.fillMaxWidth(),
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
            if(message.isNotEmpty()){
                onMessageSend(message)
                message = ""
            }
        }){
            Icon(imageVector = Icons.Default.Send,
                contentDescription = "Send" )
        }
    }


}

