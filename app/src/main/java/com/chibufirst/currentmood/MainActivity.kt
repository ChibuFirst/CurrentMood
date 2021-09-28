package com.chibufirst.currentmood

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.chibufirst.currentmood.databinding.ActivityMainBinding

const val TAG = "MainActivity"
const val MOOD_MESSAGE = "mood_message"
const val CURRENT_MOOD = "current_mood"

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private var moodMessage = ""
    private var currentMood = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val sadMood = listOf(
            "Don't be sad, it will be alright!",
            "Oh no! Somebody is sad...",
            "Hey! Smile suits you, do wear it often.",
            "Only a bad day, not a bad life. Be happy!",
            "Don't loose hope and put on a smiling face."
        )
        val happyMood = listOf(
            "Somebody is happy! Keep it up!",
            "Kudos! Continue in this happiness.",
            "Cool! Keep on smiling...",
            "Awesome! Nice mood to maintain always.",
            "Good to hear that, don't change it."
        )

        binding.apply {
            if (savedInstanceState != null) {
                currentMood = savedInstanceState.getString(CURRENT_MOOD, "")
                moodMessage =
                    savedInstanceState.getString(MOOD_MESSAGE, getString(R.string.default_mood))
                if (currentMood == "sad") {
                    cardSad.isChecked = true
                    cardHappy.isChecked = false
                } else if (currentMood == "happy") {
                    cardHappy.isChecked = true
                    cardSad.isChecked = false
                }
                moodChanger(currentMood, moodMessage)
            }

            cardSad.setOnLongClickListener {
                if (cardHappy.isChecked) {
                    cardHappy.isChecked = false
                }
                cardSad.isChecked = !cardSad.isChecked
                currentMood = if (cardSad.isChecked) "sad" else ""
                moodMessage = sadMood[(0..4).random()]
                moodChanger(currentMood, moodMessage)
                true
            }

            cardHappy.setOnLongClickListener {
                if (cardSad.isChecked) {
                    cardSad.isChecked = false
                }
                cardHappy.isChecked = !cardHappy.isChecked
                currentMood = if (cardHappy.isChecked) "happy" else ""
                moodMessage = happyMood[(0..4).random()]
                moodChanger(currentMood, moodMessage)
                true
            }
        }
    }

    private fun moodChanger(mood: String, moodMessage: String) {
        if (mood.isNotEmpty()) {
            binding.textMoodInfo.text = moodMessage
        } else {
            binding.textMoodInfo.text = getString(R.string.default_mood)
        }
    }

    override fun onStart() {
        super.onStart()
        Log.d(TAG, "onStart Called")
    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG, "onResume Called")
    }

    override fun onPause() {
        super.onPause()
        Log.d(TAG, "onPause Called")
    }

    override fun onStop() {
        super.onStop()
        Log.d(TAG, "onStop Called")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "onDestroy Called")
    }

    override fun onRestart() {
        super.onRestart()
        Log.d(TAG, "onRestart Called")
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        Log.d(TAG, "onSaveInstanceState Called")
        outState.putString(CURRENT_MOOD, currentMood)
        outState.putString(MOOD_MESSAGE, moodMessage)
    }
}