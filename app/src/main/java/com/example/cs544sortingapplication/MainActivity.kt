package com.example.cs544sortingapplication

import android.graphics.Color
import android.os.Bundle
import android.os.Handler
import android.text.Spannable
import android.text.SpannableString
import android.text.SpannableStringBuilder
import android.text.style.ForegroundColorSpan
import android.text.style.StyleSpan
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.ComponentActivity
import androidx.activity.enableEdgeToEdge
import kotlin.system.exitProcess


class MainActivity : ComponentActivity() {
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
            if (inputArray.array == null) {
                outputText.text = inputArray.errorMessage
            } else {
                submitButton.isEnabled = false // Disable the button
                val handler = Handler(mainLooper)
                var numbers: IntArray = inputArray.array
                val intermediateSteps = SpannableStringBuilder("Input Array: ${numbers.joinToString(" ")}\n")
                for (i in 0 until numbers.size) {
                    handler.postDelayed({
                        numbers = intermediateInsertionSort(numbers, i)

                        val spannableString = SpannableString(numbers.joinToString(" ") + "\n")
                        val currentElementStringIndex = i * 2 + 1
                        if(currentElementStringIndex < spannableString.length - 1) {
                            spannableString.setSpan(
                                StyleSpan(android.graphics.Typeface.BOLD),
                                currentElementStringIndex,
                                currentElementStringIndex + 2,
                                Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
                            )
                            spannableString.setSpan(
                                ForegroundColorSpan(Color.RED),
                                currentElementStringIndex,
                                currentElementStringIndex + 2,
                                Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
                            )
                        }

                        spannableString.setSpan(
                            ForegroundColorSpan(Color.BLUE),
                            0,
                            currentElementStringIndex + 1,
                            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
                        )

                        intermediateSteps.append(spannableString)
                        outputText.text = intermediateSteps

                        // Re-enable the button after the last step
                        if (i == numbers.size - 1) {
                            submitButton.isEnabled = true
                        }
                    }, i * 1000L) // 1 second delay between each step
                }
            }
        }
    }

    companion object {
        final val MIN_ELEMENTS = 3
        final val MAX_ELEMENTS = 8

        public final val ARRAY_TOO_SMALL_ERROR_TEXT = "Please enter at least 3 values"
        final val ARRAY_TOO_LARGE_ERROR_TEXT = "Please enter at most 8 values"
        final val INVALID_ELEMENT_ERROR_TEXT = "Please only enter integers between 0 and 9"
        final val VALID_ARRAY_TEXT = "Valid array"

        fun getArrayFromString(input: String): InputArray {
            val trimmedInput = input.trim()
            val inputArray = trimmedInput.split(" ").filter { it.isNotEmpty() }

            if (!inputArray.all { it.length == 1 && it[0] in '0'..'9' }) {
                return InputArray(null, INVALID_ELEMENT_ERROR_TEXT)
            }

            if (inputArray.size < MIN_ELEMENTS) {
                return InputArray(null, ARRAY_TOO_SMALL_ERROR_TEXT);
            }

            if (inputArray.size > MAX_ELEMENTS) {
                return InputArray(null, ARRAY_TOO_LARGE_ERROR_TEXT);
            }

            val numbers = inputArray.map { it.toInt() }.toMutableList()
            return InputArray(numbers.toIntArray(), VALID_ARRAY_TEXT)
        }

        fun intermediateInsertionSort(numbers: IntArray, i: Int): IntArray {
            val intermediateResult = numbers.copyOf()

            for (j in i downTo 1) {
                if (intermediateResult[j] < intermediateResult[j - 1]) {
                    val temp = intermediateResult[j]
                    intermediateResult[j] = intermediateResult[j - 1]
                    intermediateResult[j - 1] = temp
                }
            }

            return intermediateResult
        }
    }
}