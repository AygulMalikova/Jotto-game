package com.example.jotto_game

import android.content.Intent
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isClickable
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import androidx.test.rule.ActivityTestRule
import com.example.jotto_game.start.view.MainActivity
import org.hamcrest.CoreMatchers.allOf
import org.hamcrest.CoreMatchers.not
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
@LargeTest
class MainActivityInstrumentedTest {

    @get:Rule
    val activityRule = ActivityTestRule(MainActivity::class.java)

    @Before
    fun setup() {
        val activityUnderTest: MainActivity = activityRule.activity
        activityUnderTest.sendBroadcast(Intent(Intent.ACTION_CLOSE_SYSTEM_DIALOGS))
    }

    /**
     * test the depicting of logoView
     */
    @Test
    fun logoView() {
        onView(withId(R.id.logo_image)).check(matches(allOf(isDisplayed())))
        onView(withId(R.id.logo_image)).check(matches(allOf(not(isClickable()))))
    }

    /**
     *test the depicting of helper btn
     */
    @Test
    fun helperBtnView() {
        onView(withId(R.id.helper)).check(matches(allOf(isDisplayed())))
        onView(withId(R.id.helper)).check(matches(allOf(isClickable())))
    }

    /**
     *test the depicting of start btn
     */
    @Test
    fun startButton() {
        onView(withId(R.id.start_button)).check(matches(allOf(isDisplayed())))
    }

    /**
     *test the depicting of logo at setup screen
     */
    @Test
    fun logoSetupView() {
        onView(withId(R.id.logo_image)).check(matches(allOf(isDisplayed())))
        onView(withId(R.id.logo_image)).check(matches(allOf(not(isClickable()))))
    }
}
