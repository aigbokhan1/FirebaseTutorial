package com.shadrack.firebasetutorial.ui.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.shadrack.firebasetutorial.R

class SplashscreenActivity : AppCompatActivity() {

    private lateinit var runnable: Runnable
    private var handler : Handler = Handler()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splashscreen)

        Handler().postDelayed({
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }, 5000)
    }
}