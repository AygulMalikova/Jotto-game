package com.example.jotto_game


import android.animation.ObjectAnimator
import android.animation.PropertyValuesHolder
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val startBtn: Button = findViewById<Button>(R.id.start_button)//finding the start game button
        startButtonAnimation(startBtn)

        startBtn.setOnClickListener {
            startGame()
        }
    }

    /**
     * Function that add pulse animation on start button
     * Implementation was taken from https://stackoverflow.com/questions/27301586/repeat-pulse-animation
     */
    private fun startButtonAnimation(startBtn: Button) {
        val scaleDown: ObjectAnimator = ObjectAnimator.ofPropertyValuesHolder(
                startBtn,
                PropertyValuesHolder.ofFloat("scaleX", 1.2f),
                PropertyValuesHolder.ofFloat("scaleY", 1.2f))
        scaleDown.duration = 700

        scaleDown.repeatCount = ObjectAnimator.INFINITE
        scaleDown.repeatMode = ObjectAnimator.REVERSE

        scaleDown.start()
    }

    /**
     * Creating new intent with game activity and finishing current activity
     */
    private fun startGame() {
        val intent = Intent()
        intent.setClassName(this, "com.example.game.GameActivity")
        startActivity(intent)
        this.finish()
    }
}