package com.example.myapplication741

import android.content.SharedPreferences
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.widget.TextView
import android.view.View

class StatsActivity : AppCompatActivity() {

    private lateinit var textViewStats: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_stats)

        textViewStats = findViewById(R.id.textView)

        displayStats()

    }

    private fun displayStats() {
        val sharedPreferences: SharedPreferences = getSharedPreferences("MyPrefs", MODE_PRIVATE)

        val game1Result = sharedPreferences.getString("game1Result", "")
        val game2Result = sharedPreferences.getString("game2Result", "")
        val game3Result = sharedPreferences.getString("game3Result", "")

        val statsText = "Game 1: $game1Result\nGame 2: $game2Result\nGame 3: $game3Result"

        textViewStats.text = statsText
    }

    fun goBackToGame(view: View) {
        finish()
    }

}