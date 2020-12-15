package com.example.jotto_game


import android.app.Activity
import android.content.Intent
import android.provider.Settings.Global.getString
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.ActivityResultMatchers.hasResultCode
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import androidx.test.rule.ActivityTestRule
import com.example.jotto_game.finishgame.view.FinishGameActivity
import com.example.jotto_game.game.view.PlayingGameActivity
import com.example.jotto_game.start.view.MainActivity
import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import java.lang.Thread.sleep


/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
@LargeTest
class PlayingGameActivityIntrumentedTest {
    @get:Rule
    val activityRule = ActivityTestRule(PlayingGameActivity::class.java)

    @Test
    fun turnOffOnMusic() {
       // if (activityRule.activity.intent.getEx

//        val activityUnderTest: PlayingGameActivity = activityRule.activity
//        activityUnderTest.sendBroadcast(Intent(Intent.ACTION_CLOSE_SYSTEM_DIALOGS))

//        
//        onView(withId(R.id.soundButton)).perform(click())
//        assertTrue(!activityUnderTest.soundOn)
//        sleep(3000)
//        onView(withId(R.id.soundButton)).perform(click())
//        assertTrue(activityUnderTest.soundOn)


    }
//    @Test
//    fun resutTextView() {
//        val activityUnderTest: FinishGameActivity = activityRule.activity
//        activityUnderTest.sendBroadcast(Intent(Intent.ACTION_CLOSE_SYSTEM_DIALOGS))
//
//
//        val extras = activityRule.activity.intent.extras
//        val word: String
//        val numberOfTries: Int
//        val userWin: Boolean
//        if (extras != null) {
//            word = extras.getString(R.string.sourceWord.toString()).toString()
//            numberOfTries = extras.getInt(R.string.numberOfAttempts.toInt().toString()).toInt()
//            userWin = extras.getBoolean(R.string.gameResult.toString())
//
//        } else {
//            word = "None"
//            numberOfTries = -1
//            userWin = false
//        }
//        if (userWin) {
//            val res =
//                activityRule.activity.resources.getString(R.string.winMessage, numberOfTries, word)
//            onView(withId(R.id.finishTextView)).check(matches(withText(res)))
//        } else {
//
//            val res =
//                activityRule.activity.resources.getString(R.string.loseMessage, numberOfTries, word)
//            val tr = onView(withId(R.id.finishTextView)).toString()
//            onView(withId(R.id.finishTextView)).check(matches(withText(res)))
//        }
//    @Test
//    fun pressHome_openSetupActivity() {
//
//        val activityUnderTest: FinishGameActivity = activityRule.activity
//        activityUnderTest.sendBroadcast(Intent(Intent.ACTION_CLOSE_SYSTEM_DIALOGS))
//        onView(withId(R.id.finishButton)).perform(click())
//
//            }

}


