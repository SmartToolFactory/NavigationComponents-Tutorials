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

import android.content.Intent
import android.net.Uri
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.Espresso.pressBack
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDescendantOfA
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.rule.ActivityTestRule
import org.hamcrest.CoreMatchers.allOf
import org.junit.Rule
import org.junit.Test

class DeepLinkTest {

    private val userName = "Person 2"

    private val url = "http://www.example.com/user/$userName"

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    var activityTestRule = object : ActivityTestRule<MainActivity>(MainActivity::class.java) {
        override fun getActivityIntent(): Intent {
            return Intent(Intent.ACTION_VIEW, Uri.parse(url))
        }
    }

    @Test
    fun bottomNavView_DeepLink_HandlesIntent_BackGoesToList() {
        // Opening the app with the proper Intent should start it in the profile screen.
        assertInProfile()

        pressBack()

        // The list should be shown
        assertList()

        pressBack()

        // Home destination should be shown
        assertInHome()
    }

    private fun assertInProfile() {
        onView(withText(userName))
            .check(matches(isDisplayed()))
    }

    private fun assertList() {
        onView(allOf(withText(R.string.title_list), isDescendantOfA(withId(R.id.action_bar))))
            .check(matches(isDisplayed()))
    }

    private fun assertInHome() {
        onView(withText(R.string.welcome))
            .check(matches(isDisplayed()))
    }
}
