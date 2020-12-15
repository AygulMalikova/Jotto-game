package com.example.jotto_game.finishgame.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.example.jotto_game.R
import kotlinx.android.synthetic.main.activity_finish_game.*

class FinishGameActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_finish_game)
        val word: String
        val numberOfTries: Int
        val userWin: Boolean

        val extras = intent.extras
        // get info about the game results: hidden word, number of attempts were done and result(lose/win)
        if (extras != null) {
            word = extras.getString(resources.getString(R.string.sourceWord)).toString()
            numberOfTries = extras.getInt(resources.getString(R.string.numberOfAttempts))
            userWin = extras.getBoolean(resources.getString(R.string.gameResult))
        } else {
            word = "None"
            numberOfTries = -1
            userWin = false
        }

        // set picture and textdepending on result
        if (userWin) {
            finishImageView.setImageDrawable(
                ContextCompat.getDrawable(this, R.drawable.win)
            )

            val message = resources.getString(R.string.winMessage, numberOfTries, word)
            finishTextView.text = message
        } else {
            finishImageView.setImageDrawable(
                ContextCompat.getDrawable(this, R.drawable.lose)
            )
            val message = resources.getString(R.string.loseMessage, word)
            finishTextView.text = message
        }
    // return to game setup screen
        finishButton.setOnClickListener {

            this.finish()
        }
    }
}
