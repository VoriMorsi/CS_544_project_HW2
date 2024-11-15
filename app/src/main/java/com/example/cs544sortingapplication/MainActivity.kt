package com.example.cs544sortingapplication

import android.os.Bundle
import android.os.Handler
import android.widget.Button
import android.widget.EditText
import androidx.activity.ComponentActivity
import androidx.activity.enableEdgeToEdge
import android.widget.TextView
import kotlin.system.exitProcess

class MainActivity : ComponentActivity() {
    final val MIN_ELEMENTS = 3
    final val MAX_ELEMENTS = 8

    final val ARRAY_TO_SMALL_ERROR_TEXT = "Please enter at least 3 values"
    final val ARRAY_TO_LARGE_ERROR_TEXT = "Please enter at most 8 values"
    final val INVALID_ELEMENT_ERROR_TEXT = "Please only enter integers between 0 and 9"
    final val VALID_ARRAY_TEXT = "Valid array"

    data class InputArray(val array: IntArray?, val errorMessage: String)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContentView(R.layout.activity_main)

        val inputEditText: EditText = findViewById(R.id.myEditTextText)
        val submitButton: Button = findViewById(R.id.myButton)
        val outputText: TextView = findViewById(R.id.myTextView)

        submitButton.setOnClickListener {
            val inputArray = getArrayFromString(inputEditText.text.toString())
            if(inputArray.array == null) {
                outputText.text = inputArray.errorMessage
            } else {
                val numbers = inputArray.array
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
            }
        }
    }

    fun getArrayFromString(input: String): InputArray {
        val trimmedInput = input.trim()
        val inputArray = trimmedInput.split(" ").filter { it.isNotEmpty() }

        if(!inputArray.all { it.length == 1 && it[0] in '0'..'9' }) {
            return InputArray(null, INVALID_ELEMENT_ERROR_TEXT)
        }

        if(inputArray.size < MIN_ELEMENTS) {
            return InputArray(null, ARRAY_TO_SMALL_ERROR_TEXT);
        }

        if(inputArray.size > MAX_ELEMENTS) {
            return InputArray(null, ARRAY_TO_LARGE_ERROR_TEXT);
        }

        val numbers = inputArray.map { it.toInt() }.toMutableList()
        return InputArray(numbers.toIntArray(), VALID_ARRAY_TEXT)
    }
}