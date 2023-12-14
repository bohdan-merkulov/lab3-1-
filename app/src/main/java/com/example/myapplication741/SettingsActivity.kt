package com.example.myapplication741

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import android.widget.RadioGroup
import android.widget.Switch
import androidx.appcompat.app.AppCompatActivity

class SettingsActivity : AppCompatActivity() {

    private lateinit var maxAttemptsRadioGroup: RadioGroup

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)

        maxAttemptsRadioGroup = findViewById(R.id.radioGroup)
    }

    fun applySettings(view: View) {

        val sharedPreferences: SharedPreferences = getSharedPreferences("MyPrefs", MODE_PRIVATE)
        val editor: SharedPreferences.Editor = sharedPreferences.edit()

        val maxAttemptsOption: Int = when (maxAttemptsRadioGroup.checkedRadioButtonId) {
            R.id.radioButton -> 5
            R.id.radioButton2 -> 10
            R.id.radioButton3 -> 20
            else -> 10
        }
        editor.putInt("maxAttempts", maxAttemptsOption)

        editor.putBoolean("isSettingsApplied", true)

        editor.putString("game1Result", "")
        editor.putString("game2Result", "")
        editor.putString("game3Result", "")

        editor.apply()

        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }

}