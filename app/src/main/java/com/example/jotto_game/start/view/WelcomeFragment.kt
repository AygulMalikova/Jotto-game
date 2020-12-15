package com.example.jotto_game.start.view

import android.animation.ObjectAnimator
import android.animation.PropertyValuesHolder
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.example.jotto_game.R

/**
 * A simple [Fragment] subclass.
 * Use the [WelcomeFragment.newInstance] factory method to
 * create an instance of this fragment.
 */

class WelcomeFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
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

        val startBtn: Button = view.findViewById(R.id.start_button) // finding the start game button
        startButtonAnimation(startBtn)

        startBtn.setOnClickListener {
            goToSetup(view)
        }
        // button that shows a transparent window with short rules description
        val helperButton: ImageButton = view.findViewById<ImageButton>(R.id.helper)

        helperButton.setOnClickListener {
            (requireActivity() as MainActivity).setupHelper(
                helperButton,
                resources.getString(R.string.rules)
            )
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
            PropertyValuesHolder.ofFloat("scaleY", 1.2f)
        )
        scaleDown.duration = 700

        scaleDown.repeatCount = ObjectAnimator.INFINITE
        scaleDown.repeatMode = ObjectAnimator.REVERSE

        scaleDown.start()
    }

    /**
     *  Navigating to the next fragment (to setup)
     */
    private fun goToSetup(view: View) {
        view.findNavController().navigate(R.id.action_welcome_to_setup)
    }
}
