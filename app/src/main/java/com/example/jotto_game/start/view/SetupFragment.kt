package com.example.jotto_game.start.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.example.jotto_game.R
import com.google.android.material.textfield.TextInputLayout
import com.weiwangcn.betterspinner.library.material.MaterialBetterSpinner


/**
 * A simple [Fragment] subclass.
 * Use the [SetupFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class SetupFragment : Fragment() {

    private lateinit var startBtn: Button
    var difficulties = arrayOf(R.string.default_diff.toString(), R.string.medium_diff.toString(), R.string.hard_diff.toString())
    //var difficulties = arrayOf("Easy","Medium", "Hard")
    var selectedDifficulty = ""

    private lateinit var numberOfLettersInput: EditText

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_setup, container, false)
    }

    companion object {
        fun newInstance() = SetupFragment()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        startBtn = view.findViewById(R.id.start_button)//finding the start game button

        initInput(view)
        initSpinner(view)

        startBtn.setOnClickListener {
            handleClickOnStart()
        }

        val helperButton: ImageButton = view.findViewById<ImageButton>(R.id.helper)
        // explanation of difficulties meaning
        helperButton.setOnClickListener {
            (requireActivity() as MainActivity).setupHelper(
                helperButton,
                resources.getString(R.string.helper_text)
            );
        }
    }

    /**
     * Function that add initial value to the input field and sets listener on key changes for validation
     */
    private fun initInput(view: View) {
        numberOfLettersInput =
            view.findViewById<EditText>(R.id.number_of_words_input)//finding the start game button

        numberOfLettersInput.setText(getString(R.string.default_letter)); // 5 is a default number of letters in the word

        val til =
            view.findViewById(R.id.textInputLayout) as TextInputLayout

        fun showError(message: String) {
            til.error = message
            startBtn.isEnabled = false
        }

        val minLetters = getString(R.string.min_letter).toInt()
        val maxLetters = getString(R.string.max_letter).toInt()

        numberOfLettersInput.setOnKeyListener(View.OnKeyListener { v, keyCode, event ->
            val currentValue = numberOfLettersInput.text.toString().toIntOrNull()
            til.isErrorEnabled = false
            startBtn.isEnabled = true

            if (numberOfLettersInput.text == null) {
                showError(getString(R.string.not_int))
            }

            if (currentValue == null) {
                showError(getString(R.string.not_int))
            } else {
                if (currentValue < minLetters) {
                    showError(getString(R.string.too_few))
                } else if (currentValue > maxLetters) {
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
    private fun initSpinner(view: View) {
        val spinner =
            view.findViewById<View>(R.id.difficulty_spinner) as MaterialBetterSpinner

        val spinnerAdapter: ArrayAdapter<String> = ArrayAdapter<String>(
            requireContext(),
            android.R.layout.simple_dropdown_item_1line,
            difficulties
        )

        spinner.setAdapter(spinnerAdapter)

        spinner.setOnItemClickListener(AdapterView.OnItemClickListener { adapterView, view, position, l ->
            selectedDifficulty =
                adapterView.getItemAtPosition(position).toString()
        })

        // setSelection is not working for better spinner https://github.com/Lesilva/BetterSpinner/issues/92
        spinner.getEditableText()
            .append(getString(R.string.default_diff)); //setting the first choice as a default value

    }

    /**
     * Creating new intent with game activity and finishing current activity
     */
    private fun handleClickOnStart() {
        val numberOfLetters = numberOfLettersInput.text.toString()
        (requireActivity() as MainActivity).startGame(numberOfLetters, selectedDifficulty)
    }
}