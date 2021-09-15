package com.example.home.view

import androidx.test.runner.AndroidJUnit4
import junit.framework.TestCase
import org.junit.Assert.*
import org.junit.runner.RunWith
import androidx.fragment.app.testing.FragmentScenario
import androidx.fragment.app.testing.FragmentScenario.launchInContainer
import androidx.lifecycle.Lifecycle
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.matcher.ViewMatchers.withId
import com.example.home.test.R
import org.junit.Before
import org.junit.Test

@RunWith(AndroidJUnit4::class)
class MainFragmentTest: TestCase() {

    private lateinit var scenario: FragmentScenario<MainFragment>

    @Before
    fun setup() {
        scenario.moveToState(Lifecycle.State.STARTED)
    }

    @Test
    fun testView() {
        val title = "Welcome! Do a search"
        onView(withId(R.id.titleSearch)).perform(typeText(title))
    }
}