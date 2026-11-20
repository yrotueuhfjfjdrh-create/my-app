package com.example

import android.app.Activity
import android.os.Bundle
import android.speech.tts.TextToSpeech
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.example.myapp.R
import java.util.Locale

class VoiceHelperScreen : Activity(), TextToSpeech.OnInitListener {

    private lateinit var tts: TextToSpeech
    private var isTtsReady = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tts = TextToSpeech(this, this)

        val inputBox = findViewById<EditText>(R.id.inputBox)
        val speakButton = findViewById<Button>(R.id.speakButton)
        val replyText = findViewById<TextView>(R.id.replyText)

        speakButton.setOnClickListener {
            val userText = inputBox.text.toString().trim()
            if (userText.isNotEmpty()) {
                val reply = "Aapne kaha: $userText"
                replyText.text = reply
                speakOut(reply)
            }
        }
    }

    override fun onInit(status: Int) {
        if (status == TextToSpeech.SUCCESS) {
            val result = tts.setLanguage(Locale.forLanguageTag("hi-IN"))
            if (result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                tts.language = Locale.US
            }
            isTtsReady = true
        }
    }

    private fun speakOut(text: String) {
        if (isTtsReady) {
            tts.speak(text, TextToSpeech.QUEUE_FLUSH, null, "utterance_id")
        }
    }

    override fun onDestroy() {
        if (::tts.isInitialized) {
            tts.stop()
            tts.shutdown()
        }
        super.onDestroy()
    }
}
