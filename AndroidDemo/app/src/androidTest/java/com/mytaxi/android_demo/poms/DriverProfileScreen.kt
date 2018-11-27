package com.mytaxi.android_demo.poms

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import com.mytaxi.android_demo.R
import com.mytaxi.android_demo.utils.AppMatchers

class DriverProfileScreen : Screen {
    val driverProfileToolBar = AppMatchers.getToolBarTextViewInteraction("Driver Profile")
    val avatar = onView(withId(R.id.imageViewDriverAvatar))
    val driverName = onView(withId(R.id.textViewDriverName))
    val driverDateImage = onView(withId(R.id.imageViewDriverDate))
    val driverDateText = onView(withId(R.id.textViewDriverDate))
    val driverLocationImage = onView(withId(R.id.imageViewDriverLocation))
    val driverLocationText = onView(withId(R.id.textViewDriverLocation))
    val fabButton = onView(withId(R.id.fab))

    override fun verifyIsDisplayed() {
        driverProfileToolBar.check(matches(isCompletelyDisplayed()))
        avatar.check(matches(isCompletelyDisplayed()))
        driverName.check(matches(isCompletelyDisplayed()))
        driverDateImage.check(matches(isCompletelyDisplayed()))
        driverDateText.check(matches(isCompletelyDisplayed()))
        driverLocationImage.check(matches(isCompletelyDisplayed()))
        driverLocationText.check(matches(isCompletelyDisplayed()))
        fabButton.check(matches(isCompletelyDisplayed()))
    }

    fun verifyDriverName(text: String) {
        driverName.check(matches(withText(text)))
    }

    // Driver details verification methods may look like

    fun verifyDriverDate(text: String) {
        driverDateText.check(matches(withText(text)))
    }

    fun verifyDriverLocation(text: String) {
        driverLocationText.check(matches(withText(text)))
    }

    fun clickCall() {
        fabButton.perform(click())
    }
}