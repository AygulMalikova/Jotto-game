package com.example.jotto_game.start.view

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.example.jotto_game.R
import com.google.android.material.textfield.TextInputLayout
import com.skydoves.balloon.ArrowOrientation
import com.skydoves.balloon.BalloonAnimation
import com.skydoves.balloon.createBalloon
import com.skydoves.balloon.showAlignBottom
import com.weiwangcn.betterspinner.library.material.MaterialBetterSpinner


class SetupActivity : AppCompatActivity() {
    lateinit var spinner: MaterialBetterSpinner //spinner
    private lateinit var startBtn: Button
    var difficulties = arrayOf("Easy", "Medium", "Hard")
    var selectedDifficulty = ""

    val helperText = "Different difficulty of the game depends on the number of attempts. " +
            "The easy option has no limitations, medium has 15 attempts and the hard 7."

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_setup)
        startBtn = findViewById<Button>(R.id.start_button)//finding the start game button

        initInput()
        initSpinner()

        startBtn.setOnClickListener {
            startGame()
        }

        val helperButton: ImageButton = findViewById<ImageButton>(R.id.helper)

        helperButton.setOnClickListener {
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
            helperButton.showAlignBottom(balloon)
        }

    }

    /**
     * Function that add initial value to the input field and sets listener on key changes for validation
     */
    private fun initInput() {
        val numberOfWordsInput: EditText = findViewById<EditText>(R.id.number_of_words_input)//finding the start game button

        numberOfWordsInput.setText(getString(R.string.default_letter)); // 5 is a default number of letters in the word

        val til =
            findViewById<View>(R.id.textInputLayout) as TextInputLayout

        fun showError(message: String) {
            til.error = message
            startBtn.isEnabled = false
        }

        val minLetters = getString(R.string.min_letter).toInt()
        val maxLetters = getString(R.string.max_letter).toInt()

        numberOfWordsInput.setOnKeyListener(View.OnKeyListener { v, keyCode, event ->
            val currentValue = numberOfWordsInput.text.toString().toIntOrNull()
            til.isErrorEnabled = false
            startBtn.isEnabled = true

            if (numberOfWordsInput.text == null) {
                showError(getString(R.string.not_int))
            }

            if (currentValue == null) {
                showError(getString(R.string.not_int))
            } else {
                if (currentValue < minLetters) {
                    showError(getString(R.string.too_few))
                } else if (currentValue > maxLetters ) {
                    showError(getString(R.string.too_much))
                }
            }
            false
        }
        )
    }

    /**
     * Function that initiates spinner by setting adapter and click listener handler
     */
    private fun initSpinner() {
        spinner =
            findViewById<View>(R.id.difficulty_spinner) as MaterialBetterSpinner

        val spinnerAdapter: ArrayAdapter<String> = ArrayAdapter<String>(
            this@SetupActivity,
            android.R.layout.simple_dropdown_item_1line,
            difficulties
        )

        spinner.setAdapter(spinnerAdapter)



        spinner.setOnItemClickListener(AdapterView.OnItemClickListener { adapterView, view, position, l ->
            selectedDifficulty =
                adapterView.getItemAtPosition(position).toString()
        })

        // setSelection is not working for better spinner https://github.com/Lesilva/BetterSpinner/issues/92
        spinner.getEditableText().append("Easy"); //setting the first choice as a default value

    }

    /**
     * Creating new intent with game activity and finishing current activity
     */
    private fun startGame() {
        val intent = Intent()
        intent.setClassName(this, "com.example.jotto_game.game.view.PlayingGameActivity")
        startActivity(intent)
        this.finish()
    }
}