package com.example.jotto_game


import android.content.Context
import android.content.Intent
import android.os.Build
import android.widget.TextView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import androidx.test.rule.ActivityTestRule
import com.example.jotto_game.finishgame.view.FinishGameActivity
import org.hamcrest.CoreMatchers.allOf
import org.junit.Assert.assertTrue
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
class FinishGameActivityInstrumentedTest {
    var context: Context? = null

    @get:Rule
    val activityRule = ActivityTestRule(FinishGameActivity::class.java)

    @Before
    fun setup() {
        val activityUnderTest: FinishGameActivity = activityRule.activity
        activityUnderTest.sendBroadcast(Intent(Intent.ACTION_CLOSE_SYSTEM_DIALOGS))

    }

    /**
     * test the depicting of resultPictureView
     */
    @Test
    fun resultPictureView() {
        onView(withId(R.id.finishImageView)).check(matches(allOf(isDisplayed())))
    }

    /**
     * test the depicting of resultTextView
     */
    @Test
    fun resultTextView() {
        onView(withId(R.id.finishTextView)).check(matches(isDisplayed()))
        val t = onView(withId(R.id.finishTextView)).toString()
        assertTrue(t.isNotEmpty())

    }

    /**
     * test the depicting of homeButton
     */
    @Test
    fun homeButtonView() {
        onView(withId(R.id.finishButton)).check(matches(isDisplayed()))
        onView(withId(R.id.finishButton)).check(matches(withText(R.string.finishHomeBtn)))
    }

    /**
     * test finishing of FinishGameActivity by press the home button
     */
    @Test
    fun pressHome_openSetupActivity() {

        val activityUnderTest: FinishGameActivity = activityRule.activity
        activityUnderTest.sendBroadcast(Intent(Intent.ACTION_CLOSE_SYSTEM_DIALOGS))
        onView(withId(R.id.finishButton)).perform(click())

        Thread.sleep(2000)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            assertTrue(activityRule.activity.isDestroyed)
        }
    }

}



