package com.example.jotto_game.start.view

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import com.example.jotto_game.R
import com.example.jotto_game.game.view.PlayingGameActivity
import com.example.jotto_game.GameApplication
import com.example.jotto_game.game.domain.WordViewModel
import com.example.jotto_game.game.sharedpreferences.SharedPreferencesWrapper
import com.skydoves.balloon.*
import javax.inject.Inject
import androidx.lifecycle.Observer


class MainActivity : AppCompatActivity() {

    @Inject lateinit var wordViewModel: WordViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        (application as GameApplication).appComponent.inject(this)

        // subscribe on secret word
        wordViewModel.secretWord.observe(this, Observer { lastWord ->
            if (lastWord != "") {
                // if there is already a secret word then continue a game
                startGame(getString(R.string.default_letter), getString(R.string.default_diff))
            }
        })
    }

    /**
     * Function that create balloon for helper button with given helper text
     */
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

        intent.putExtra(getString(R.string.letters), numberOfLetters)
        intent.putExtra(getString(R.string.difficulty), difficulty)

        startActivity(intent)

        this.finish()
    }
}