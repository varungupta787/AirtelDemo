package com.airtel.demo.presentation.ui

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.airtel.demo.R
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        moveToSuggestionScreen()
    }

    fun moveToSuggestionScreen() = runBlocking {
        delay(2000)
            startActivity(Intent(this@SplashActivity,
                    AutoSuggestionActivity::class.java))
        finish()
    }
}
