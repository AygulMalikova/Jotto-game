package com.example.jotto_game

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textfield.TextInputLayout
import com.weiwangcn.betterspinner.library.material.MaterialBetterSpinner


class SetupActivity : AppCompatActivity() {
    lateinit var spinner: MaterialBetterSpinner //spinner
    var difficulties = arrayOf("Easy", "Medium", "Hard")
    var selectedDifficulty = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_setup)

        initInput()
        initSpinner()
    }

    /**
     * Function that add initial value to the input field and sets listener on key changes for validation
     */
    private fun initInput() {
        val numberOfWordsInput: EditText = findViewById<EditText>(R.id.number_of_words_input)//finding the start game button

        numberOfWordsInput.setText("5"); // 5 is a default number of letters in the word

        val til =
            findViewById<View>(R.id.textInputLayout) as TextInputLayout

        numberOfWordsInput.setOnKeyListener(View.OnKeyListener { v, keyCode, event ->
            val currentValue = numberOfWordsInput.text.toString().toIntOrNull()
            til.isErrorEnabled = false

            if (currentValue == null) {
                til.error = R.string.not_int.toString()
            } else {
                if (currentValue < 3) {
                    til.error = R.string.too_few.toString()
                } else if (currentValue > 10 ) {
                    til.error =  R.string.too_much.toString()
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
    }
}