package com.example.anagramapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import com.example.anagramapp.ui.theme.AnagramAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AnagramAppTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    AnagramScreen()
                }
            }
        }
    }
}

@Composable
fun AnagramScreen() {
    var inputText by remember { mutableStateOf(TextFieldValue("")) }
    var filterText by remember { mutableStateOf(TextFieldValue("")) }

    // Wrap `derivedStateOf` in a `remember` block
    val anagramResult by remember(inputText.text, filterText.text) {
        derivedStateOf {
            Anagram.build(inputText.text, filterText.text)
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Text(
            text = "Enter Text:",
            style = MaterialTheme.typography.titleMedium
        )
        BasicTextField(
            value = inputText,
            onValueChange = { inputText = it },
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        )

        Text(
            text = "Enter Filter Characters:",
            style = MaterialTheme.typography.titleMedium
        )
        BasicTextField(
            value = filterText,
            onValueChange = { filterText = it },
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "Anagram Result:",
            style = MaterialTheme.typography.titleMedium
        )
        Text(
            text = anagramResult,
            style = MaterialTheme.typography.bodyLarge
        )
    }
}
