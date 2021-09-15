package com.example.home.view

import androidx.test.runner.AndroidJUnit4
import junit.framework.TestCase
import org.junit.Assert.*
import org.junit.runner.RunWith
import androidx.fragment.app.testing.FragmentScenario
import androidx.fragment.app.testing.FragmentScenario.launchInContainer
import com.example.home.test.R
import org.junit.Before

@RunWith(AndroidJUnit4::class)
class MainFragmentTest: TestCase() {

    private lateinit var scenario: FragmentScenario<MainFragment>


    @Before
    fun setup() {
//        scenario = FragmentScenario.launchInContainer(themeResId = R.style.AppTheme)
    }
}