package com.example.anagramapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.anagramapp.ui.theme.AnagramAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
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
            text = "Anagram Generator",
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        TextField(
            value = inputText,
            onValueChange = { inputText = it },
            label = { Text("Enter Text") },
            modifier = Modifier.fillMaxWidth()
        )

        TextField(
            value = filterText,
            onValueChange = { filterText = it },
            label = { Text("Enter Filter Characters") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "Anagram Result:",
            style = MaterialTheme.typography.titleMedium
        )
        Text(
            text = anagramResult,
            style = MaterialTheme.typography.bodyLarge,
            fontSize = 20.sp
        )
    }
}

object Anagram {
    fun build(input: String, filter: String): String {
        val words = input.split(" ")
        val result = words.map { word ->
            val chars = word.toCharArray()
            var left = 0
            var right = chars.size - 1

            while (left < right) {
                if (filter.contains(chars[left])) {
                    left++
                    continue
                }
                if (filter.contains(chars[right])) {
                    right--
                    continue
                }

                chars[left] = chars[right].also { chars[right] = chars[left] }
                left++
                right--
            }

            String(chars)
        }

        return result.joinToString(" ")
    }
}