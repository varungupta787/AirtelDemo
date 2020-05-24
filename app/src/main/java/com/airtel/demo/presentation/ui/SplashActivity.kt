package com.airtel.demo.presentation.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity

import android.os.Bundle
import android.os.Handler
import com.airtel.demo.R

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        moveToSuggestionScreen()
    }

    private fun moveToSuggestionScreen() {
        Handler().postDelayed( {
            startActivity(Intent(this@SplashActivity,
                    AutoSuggestionActivity::class.java))}, 2000)
        finish()
    }
}
