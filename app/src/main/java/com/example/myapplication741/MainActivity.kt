package com.example.myapplication741

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import kotlin.random.Random
import android.content.Intent
import android.content.SharedPreferences

class MainActivity : AppCompatActivity() {

    private lateinit var editTextGuess: EditText
    private lateinit var buttonSubmit: Button
    private lateinit var buttonRestart: Button
    private lateinit var buttonSettings: Button
    private lateinit var textViewResult: TextView

    private var secretNumber = 0
    private var numberOfAttempts = 0
    private var maxAttempts = 10
    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sharedPreferences = getSharedPreferences("MyPrefs", MODE_PRIVATE)
        val isSettingsApplied = sharedPreferences.getBoolean("isSettingsApplied", false)

        if (!isSettingsApplied) {
            val intent = Intent(this, SettingsActivity::class.java)
            startActivity(intent)
            finish()
        } else {
            setContentView(R.layout.activity_main)

            editTextGuess = findViewById(R.id.editTextText)
            buttonSubmit = findViewById(R.id.button)
            buttonRestart = findViewById(R.id.button2)
            buttonSettings = findViewById(R.id.button5)
            textViewResult = findViewById(R.id.textView3)

            initializeGame()
        }

    }

    private fun initializeGame() {

        maxAttempts = sharedPreferences.getInt("maxAttempts", 10)

        secretNumber = Random.nextInt(1, 101)
        numberOfAttempts = 0
        textViewResult.text = ""
        buttonSubmit.visibility = View.VISIBLE
        buttonRestart.visibility = View.GONE
    }

    fun submitGuess(view: View) {
        val userGuess = editTextGuess.text.toString().toIntOrNull()

        if (userGuess != null) {
            numberOfAttempts++

            if (numberOfAttempts <= maxAttempts) {
                if (userGuess == secretNumber) {
                    textViewResult.text =
                        "Congratulations! You guessed the number in $numberOfAttempts attempts."
                            val sharedPreferences: SharedPreferences = getSharedPreferences("MyPrefs", MODE_PRIVATE)
                            val editor: SharedPreferences.Editor = sharedPreferences.edit()
                            val gameResult = "Guessed in $numberOfAttempts attempts"
                            editor.putString("game1Result", gameResult)
                            editor.putString("game2Result", sharedPreferences.getString("game1Result", ""))
                            editor.putString("game3Result", sharedPreferences.getString("game2Result", ""))
                            editor.apply()
                    buttonSubmit.visibility = View.GONE
                    buttonRestart.visibility = View.VISIBLE
                } else if (userGuess < secretNumber) {
                    textViewResult.text = "Try higher. Attempts: $numberOfAttempts"
                } else {
                    textViewResult.text = "Try lower. Attempts: $numberOfAttempts"
                }
            } else {
                textViewResult.text =
                    "You use all attempts. Restart now"
                buttonSubmit.visibility = View.GONE
                buttonRestart.visibility = View.VISIBLE
                buttonSettings.visibility = View.VISIBLE
            }
        } else {
            textViewResult.text = "Please enter a valid number."
        }

        editTextGuess.text.clear()
    }

    fun restartGame(view: View) {
        initializeGame()
    }
    fun openSettingsActivity(view: View) {
        val intent = Intent(this, SettingsActivity::class.java)
        startActivity(intent)
    }

    fun openStatsActivity(view: View) {
        val intent = Intent(this, StatsActivity::class.java)
        startActivity(intent)
    }
}