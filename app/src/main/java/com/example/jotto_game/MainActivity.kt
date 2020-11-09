package com.example.jotto_game


import android.animation.ObjectAnimator
import android.animation.PropertyValuesHolder
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        startButtonAnimation()

    }

    //https://stackoverflow.com/questions/27301586/repeat-pulse-animation
    private fun startButtonAnimation() {
        val startBtn: Button = findViewById<Button>(R.id.start_button)

        val scaleDown: ObjectAnimator = ObjectAnimator.ofPropertyValuesHolder(
                startBtn,
                PropertyValuesHolder.ofFloat("scaleX", 1.2f),
                PropertyValuesHolder.ofFloat("scaleY", 1.2f))
        scaleDown.duration = 700

        scaleDown.repeatCount = ObjectAnimator.INFINITE
        scaleDown.repeatMode = ObjectAnimator.REVERSE

        scaleDown.start()
    }
}