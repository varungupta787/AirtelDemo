package com.airtel.demo

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.rule.ActivityTestRule
import com.airtel.demo.presentation.ui.SplashActivity
import io.mockk.Runs
import io.mockk.every
import io.mockk.just
import org.junit.After
import org.junit.Before
import org.junit.Test


class SplashActivityTest {

    val splashActivityTestRule: ActivityTestRule<SplashActivity> =
            ActivityTestRule(SplashActivity::class.java, true, false)


    @Before
    fun setUp() {
    }

    @After
    fun tearDown() {

    }


    /*@Test
    fun launchSplashActivitySuccess() {
        val splashActivity = splashActivityTestRule.activity


        every { splashActivity.moveToSuggestionScreen() } just Runs
        splashActivityTestRule.launchActivity(null)

         onView(withId(R.id.splash_title)).check(ViewAssertions.matches(withText(R.string.airtel)))
        onView(withId(R.id.splash_msg)).check(ViewAssertions.matches(withText(R.string.autosuggestion_box)))

    }*/


    @Test
    fun launchAutoSuggestionActivityFromSpalshActivityWithDelay() {
        val idleResource = ElapsedTimeIdlingResource(2000)
        IdlingRegistry.getInstance().register(idleResource)

        splashActivityTestRule.launchActivity(null)

        onView(withId(R.id.search_edittext)).check(ViewAssertions.matches(isDisplayed()))
        IdlingRegistry.getInstance().unregister(idleResource)

    }
}