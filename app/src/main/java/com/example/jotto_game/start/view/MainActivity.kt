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


class MainActivity : AppCompatActivity() {
    var isPlaying: Boolean = false; //variable for handling state of application when user closes the app
    var letters = ""
    var diff= ""

    private lateinit var sharedPreferences: SharedPreferences;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        sharedPreferences = getSharedPreferences("my_dictionary", Context.MODE_PRIVATE)

        val secretWord = sharedPreferences.getString("secretwordkey", "")

        println(secretWord)
        if (secretWord != "") {
            startGame("5", "Easy")
        }
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

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        //saving is logged in state of the user
        outState.putBoolean(getString(R.string.is_playing), isPlaying);
        outState.putString(getString(R.string.letters), letters);
        outState.putString(getString(R.string.difficulty), diff);
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        // getting saved instance state
        val isPlayingState = savedInstanceState.getBoolean(getString(R.string.is_playing), false)
        val numberOfLetters = savedInstanceState.getString(getString(R.string.letters),  getString(R.string.default_letter))
        val difficulty = savedInstanceState.getString(getString(R.string.difficulty), getString(R.string.default_diff))
        //if user was logged in when redirect to the main page
        println(isPlayingState)
        if (isPlayingState) {
            startGame(numberOfLetters, difficulty)
        }
    }

    /**
     * Creating new intent with game activity and finishing current activity
     */
    fun startGame(numberOfLetters: String, difficulty: String) {
        val intent = Intent(this, PlayingGameActivity::class.java)

        intent.putExtra(getString(R.string.letters), numberOfLetters)
        intent.putExtra(getString(R.string.difficulty), difficulty)

        startActivity(intent)

        isPlaying = true;
        letters = numberOfLetters
        diff = difficulty

        this.finish()
    }
}