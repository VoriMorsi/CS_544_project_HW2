package com.example.cs544sortingapplication

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.cs544sortingapplication.ui.theme.CS544SortingApplicationTheme
import android.widget.TextView


class MainActivity : ComponentActivity() {

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContentView(R.layout.activity_main)

        val inputEditText: EditText = findViewById(R.id.myEditTextText)
        val submitButton: Button = findViewById(R.id.myButton)
        val outputText: TextView = findViewById(R.id.myTextView)

        submitButton.setOnClickListener {
            val inputText = inputEditText.text.toString().trim()
            val inputArray = inputText.split(" ").filter { it.isNotEmpty() }

            // Check if each element in inputArray is a single digit between 0 and 9
            if (inputArray.all { it.length == 1 && it[0] in '0'..'9' }) {
                // If all elements are valid, display them
                outputText.text = inputArray.joinToString(", ")
            } else {
                // Show error if any element is not a single digit
                outputText.text = "Please enter the array in the format 'digit digit " +
                        "digit digit' where digits are between 0-9"
            }
        }
    }
}