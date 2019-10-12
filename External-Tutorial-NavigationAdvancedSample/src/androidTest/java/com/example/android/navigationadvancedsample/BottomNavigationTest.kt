/*
 * Copyright 2019, The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.android.navigationadvancedsample

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.espresso.Espresso
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.Espresso.pressBack
import androidx.test.espresso.NoActivityResumedException
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDescendantOfA
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withContentDescription
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.rule.ActivityTestRule
import junit.framework.Assert.fail
import org.hamcrest.CoreMatchers.allOf
import org.junit.Rule
import org.junit.Test


class BottomNavigationTest {

    @get:Rule var instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule var activityTestRule = ActivityTestRule(MainActivity::class.java)

    @Test
    fun bottomNavView_clickOnAllItems() {
        // All screens open at their first destinations
        assertFirstScreen()

        openThirdScreen()

        assertThirdScreen()

        openSecondScreen()

        assertSecondScreen()

        openFirstScreen()

        assertFirstScreen()
    }

    @Test
    fun bottomNavView_backGoesToFirstItem() {
        // From the 2nd or 3rd screens, back takes you to the 1st.
        openThirdScreen()

        Espresso.pressBack()

        assertFirstScreen()
    }

    @Test(expected = NoActivityResumedException::class)
    fun bottomNavView_backfromFirstItemExits() {
        // From the first screen, back finishes the activity
        assertFirstScreen()

        Espresso.pressBack() // This should throw NoActivityResumedException

        fail() // If it doesn't throw
    }

    @Test
    fun bottomNavView_backstackMaintained() {
        // The back stack of any screen is maintained when returning to it
        openThirdScreen()

        onView(withContentDescription(R.string.sign_up))
                .perform(click())

        assertDeeperThirdScreen()

        openFirstScreen()

        // Return to 3rd
        openThirdScreen()

        // Assert it maintained the back stack
        assertDeeperThirdScreen()
    }

    @Test
    fun bottomNavView_registerBackRegister() {
        openThirdScreen()

        pressBack() // This is handled in a especial way in code.

        openThirdScreen()

        onView(withContentDescription(R.string.sign_up))
            .perform(click())

        // Assert it maintained the back stack
        assertDeeperThirdScreen()
    }

    @Test
    fun bottomNavView_itemReselected_goesBackToStart() {
        openThirdScreen()

        assertThirdScreen()

        onView(withContentDescription(R.string.sign_up))
            .perform(click())

        assertDeeperThirdScreen()

        // Reselect the current item
        openThirdScreen()

        // Verify that it popped the back stack until the start destination.
        assertThirdScreen()
    }

    private fun assertSecondScreen() {
        onView(allOf(withText(R.string.title_list), isDescendantOfA(withId(R.id.action_bar))))
                .check(matches(isDisplayed()))
    }

    private fun openSecondScreen() {
        onView(allOf(withContentDescription(R.string.title_list), isDisplayed()))
                .perform(click())
    }

    private fun assertDeeperThirdScreen() {
        onView(withText(R.string.done))
                .check(matches(isDisplayed()))
    }

    private fun openFirstScreen() {
        onView(allOf(withContentDescription(R.string.title_home), isDisplayed()))
                .perform(click())
    }

    private fun assertFirstScreen() {
        onView(withText(R.string.welcome))
                .check(matches(isDisplayed()))
    }

    private fun openThirdScreen() {
        onView(allOf(withContentDescription(R.string.title_register), isDisplayed()))
                .perform(click())
    }

    private fun assertThirdScreen() {
        onView(withText(R.string.select_an_avatar))
                .check(matches(isDisplayed()))
    }
}
