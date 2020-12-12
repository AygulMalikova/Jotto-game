package com.example.jotto_game.start.view

import android.animation.ObjectAnimator
import android.animation.PropertyValuesHolder
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import androidx.navigation.findNavController
import com.example.jotto_game.R
import com.skydoves.balloon.ArrowOrientation
import com.skydoves.balloon.BalloonAnimation
import com.skydoves.balloon.createBalloon
import com.skydoves.balloon.showAlignBottom

/**
 * A simple [Fragment] subclass.
 * Use the [WelcomeFragment.newInstance] factory method to
 * create an instance of this fragment.
 */

const val rules = "The rules of the game are quite simple.\n" +
        "        Based on your preferences for word length and difficulty, a word is generated at random.\n" +
        "        Your task is to guess this word.\n" +
        "        As a hint, information about your previous attempts with the number of matching letters will be displayed.\n" +
        "        Have a fun!"

class WelcomeFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_welcome, container, false)
    }

    companion object {
        fun newInstance() = WelcomeFragment()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val startBtn: Button = view.findViewById(R.id.start_button)//finding the start game button
        startButtonAnimation(startBtn)

        startBtn.setOnClickListener {
            goToSetup(view)
        }

        val helperButton: ImageButton = view.findViewById<ImageButton>(R.id.helper)

        helperButton.setOnClickListener {
            (requireActivity() as MainActivity).setupHelper(helperButton, rules);
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
    private fun goToSetup(view: View) {
        view.findNavController().navigate(R.id.action_welcome_to_setup)
    }
}