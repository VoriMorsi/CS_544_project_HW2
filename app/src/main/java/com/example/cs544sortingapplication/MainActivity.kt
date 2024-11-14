package com.example.cs544sortingapplication

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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContentView(R.layout.activity_main)

        val inputEditText: EditText = findViewById(R.id.myEditTextText)
        val submitButton: Button = findViewById(R.id.myButton)
        val outputText: TextView = findViewById(R.id.myTextView)

        submitButton.setOnClickListener{
            val inputText = inputEditText.text.toString().trim()
            val inputArray = inputText.split(Regex("[\\s,]+")).filter { it.isNotEmpty() }
            outputText.text = inputArray.joinToString(", ")
        }
    }
}