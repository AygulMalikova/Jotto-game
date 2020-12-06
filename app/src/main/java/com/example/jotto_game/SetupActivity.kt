package com.example.jotto_game

import android.os.Bundle
import android.view.View
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textfield.TextInputLayout


class SetupActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_setup)

        val numberOfWordsInput: EditText = findViewById<EditText>(R.id.number_of_words_input)//finding the start game button

        numberOfWordsInput.setText("5"); // 5 is a default number of letters in the word

        val til =
            findViewById<View>(R.id.textInputLayout) as TextInputLayout

        numberOfWordsInput.setOnKeyListener(View.OnKeyListener { v, keyCode, event ->
            val currentValue = numberOfWordsInput.text.toString().toIntOrNull()
            til.isErrorEnabled = false

            if (currentValue == null) {
                til.error = "Enter integer number"
            } else {
                if (currentValue < 3) {
                    til.error = "Too few letters"
                } else if (currentValue > 10 ) {
                    til.error = "Too much letters"
                }
            }

            false
        }

        )


    }
}