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
        val quitButton: Button = findViewById(R.id.quitButton)

        quitButton.setOnClickListener {
            exitProcess(0)
        }

        submitButton.setOnClickListener {
            val inputArray = getArrayFromString(inputEditText.text.toString())
            if(inputArray.array == null) {
                outputText.text = inputArray.errorMessage
            } else {
                val handler = Handler(mainLooper)
                var numbers: IntArray = inputArray.array
                val intermediateSteps = StringBuilder("Input Array: ${numbers.joinToString(" ")}\n")
                for (i in 1 until numbers.size) {
                    handler.postDelayed({
                        numbers = intermediateInsertionSort(numbers, i)
                        intermediateSteps.append(numbers.joinToString(" ")).append("\n")
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

    fun intermediateInsertionSort(numbers: IntArray, i: Int): IntArray {
        val intermediateResult = numbers.copyOf()

        for(j in i downTo 1) {
            if(intermediateResult[j] < intermediateResult[j - 1]) {
                val temp = intermediateResult[j]
                intermediateResult[j] = intermediateResult[j - 1]
                intermediateResult[j - 1] = temp
            }
        }

        return intermediateResult
    }
}