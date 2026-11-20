package com.example

import android.app.Activity
import android.graphics.Color
import android.os.Bundle
import android.view.Gravity
import android.widget.LinearLayout
import android.widget.TextView

class MainActivity : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val injectedToken = "APNA_TOKEN_YAHAN"

        val root = LinearLayout(this).apply {
            orientation = LinearLayout.VERTICAL
            gravity = Gravity.CENTER
            setBackgroundColor(Color.parseColor("#121212"))
            setPadding(40, 40, 40, 40)
        }

        val tv = TextView(this).apply {
            text = "My App Ready\nToken Loaded: " + (if (injectedToken.isNotEmpty() && injectedToken != "APNA_TOKEN_YAHAN") "YES" else "NO")
            textSize = 20f
            setTextColor(Color.WHITE)
            gravity = Gravity.CENTER
        }

        root.addView(tv)
        setContentView(root)
    }
}
