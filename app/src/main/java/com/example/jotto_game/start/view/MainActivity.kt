package com.example.jotto_game.start.view

import android.animation.ObjectAnimator
import android.animation.PropertyValuesHolder
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import com.example.jotto_game.R
import com.skydoves.balloon.*

val rules = "The rules of the game are quite simple.\n" +
        "        Based on your preferences for word length, difficulty and other parameters, a word is generated at random.\n" +
        "        Your task is to guess this word.\n" +
        "        As a hint, information about your previous attempts with the number of matching letters will be displayed.\n" +
        "        Have a fun!"

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val startBtn: Button = findViewById<Button>(R.id.start_button)//finding the start game button
        startButtonAnimation(startBtn)

        startBtn.setOnClickListener {
            goToSetup()
        }

        val helperButton: ImageButton = findViewById<ImageButton>(R.id.helper)

        helperButton.setOnClickListener {
            val balloon = createBalloon(baseContext) {
                setArrowSize(10)
                setWidthRatio(0.8f)
                setArrowPosition(0.85f)
                setCornerRadius(7f)
                setAlpha(0.9f)
                setText(rules)
                setTextSize(18f)
                setTextColorResource(R.color.black)
                setArrowOrientation(ArrowOrientation.TOP)
                setBackgroundColorResource(R.color.design_default_color_background)
                setBalloonAnimation(BalloonAnimation.FADE)
                setPadding(10)
                setLifecycleOwner(lifecycleOwner)
            }
            helperButton.showAlignBottom(balloon)
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
    private fun goToSetup() {
        val intent = Intent()
        intent.setClassName(this, "com.example.jotto_game.start.view.SetupActivity")
        startActivity(intent)
        //this.finish()
    }
}