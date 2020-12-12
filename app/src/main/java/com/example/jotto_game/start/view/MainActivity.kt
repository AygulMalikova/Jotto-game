package com.example.jotto_game.start.view

import android.animation.ObjectAnimator
import android.animation.PropertyValuesHolder
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import com.example.jotto_game.R
import com.example.jotto_game.game.view.PlayingGameActivity
import com.skydoves.balloon.*


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    }

    fun setupHelper(helperBtn: ImageButton, helperText: String) {
        val balloon = createBalloon(baseContext) {
            setArrowSize(10)
            setWidthRatio(0.8f)
            setArrowPosition(0.85f)
            setCornerRadius(7f)
            setAlpha(0.9f)
            setText(helperText)
            setTextSize(18f)
            setTextColorResource(R.color.black)
            setArrowOrientation(ArrowOrientation.TOP)
            setBackgroundColorResource(R.color.design_default_color_background)
            setBalloonAnimation(BalloonAnimation.FADE)
            setPadding(10)
            setLifecycleOwner(lifecycleOwner)
        }
        helperBtn.showAlignBottom(balloon)
    }

    /**
     * Creating new intent with game activity and finishing current activity
     */
    fun startGame(numberOfLetters: String, difficulty: String) {
        val intent = Intent(this, PlayingGameActivity::class.java)

        intent.putExtra("numberOfLetters", numberOfLetters)
        intent.putExtra("difficulty", difficulty)

        startActivity(intent)
        this.finish()
    }
}