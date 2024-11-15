package com.example.cs544sortingapplication

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.Handler
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

            if (inputArray.size in 3..8 && inputArray.all { it.length == 1 && it[0] in '0'..'9' }) {
                // Convert input to a list of integers
                val numbers = inputArray.map { it.toInt() }.toMutableList()
                val intermediateSteps = StringBuilder("Input Array: ${numbers.joinToString(" ")}\n")

                // Handler to schedule delayed tasks
                val handler = Handler(mainLooper)

                // Display initial array immediately
                outputText.text = intermediateSteps.toString()

                // Perform insertion sort with intermediate steps and delays
                for (i in 1 until numbers.size) {
                    val key = numbers[i]
                    var j = i - 1

                    // Post delayed task to run the sorting step
                    handler.postDelayed({
                        while (j >= 0 && numbers[j] > key) {
                            numbers[j + 1] = numbers[j]
                            j--
                        }
                        numbers[j + 1] = key

                        // Append current state of the array to intermediate steps
                        intermediateSteps.append(numbers.joinToString(" ")).append("\n")

                        // Update the TextView with the current state
                        outputText.text = intermediateSteps.toString()

                    }, i * 1000L) // 1 second delay between each step
                }
            } else if (inputArray.size < 3 || inputArray.size > 8) {
                outputText.text = "Please enter at least 3 digits and a max of 8 digits"
            } else {
                outputText.text = "Please enter the array in the format 'digit digit digit ...' where digits are between 0-9"
            }
        }
    }
}