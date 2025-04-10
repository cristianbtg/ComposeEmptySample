package com.example.compose_empty_sample

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement.Absolute.spacedBy
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.compose_empty_sample.ui.theme.ComposeEmptySampleTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ComposeEmptySampleTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    ChatScreen(modifier = Modifier.padding(innerPadding), TextFieldState())
                }
            }
        }
    }
}

@Composable
fun ChatScreen(modifier: Modifier = Modifier, textFieldState: TextFieldState) {
    Column(
        modifier = modifier.fillMaxSize()
    ) {
        LazyColumn(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
        ) {
            // Messages would be displayed here
        }

        // Input field at the bottom
        ChatInputField(
            textFieldState
        )
    }
}

@Composable
fun ChatInputField(
    textFieldState: TextFieldState,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .background(Color.Gray)
            .padding(horizontal = 16.dp, vertical = 8.dp),
        horizontalArrangement = spacedBy(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Text input field
        BasicTextField(
            state = textFieldState,
            modifier = Modifier
                .weight(1f)
                .background(Color.White)
                .clip(RoundedCornerShape(24.dp))
                .padding(horizontal = 16.dp, vertical = 12.dp),
            decorator = { innerTextField ->
                if (textFieldState.text.isEmpty()) {
                    Text(
                        text = "Type a message",
                        color = Color.Gray,
                    )
                }
                innerTextField()
            }
        )

        // Send button
        Button(
            onClick = {
                
            },
            enabled = textFieldState.text.isNotEmpty(),
            shape = RoundedCornerShape(24.dp),
            modifier = Modifier.defaultMinSize(minWidth = 48.dp, minHeight = 48.dp),
            colors = ButtonColors(
                containerColor = Color.Green,
                contentColor = Color.White,
                disabledContainerColor = Color.DarkGray,
                disabledContentColor = Color.LightGray,
            ),
            interactionSource = remember { MutableInteractionSource() },
        ) {
            Text("Send")
        }
    }
}