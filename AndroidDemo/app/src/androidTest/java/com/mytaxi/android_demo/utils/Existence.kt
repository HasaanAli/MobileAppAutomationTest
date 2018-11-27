package com.mytaxi.android_demo.utils

import androidx.test.espresso.assertion.ViewAssertions.doesNotExist
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import org.hamcrest.CoreMatchers.not

/**
 * Utility class for commonly used existence related ViewAssertions.
 */
object Existence {
    val IsCompletelyDisplayed = matches(isCompletelyDisplayed())
    val IsDisplayed = matches(isDisplayed())
    val IsNotDisplayed = matches(not(isDisplayed()))
    val Exists = matches(withEffectiveVisibility(Visibility.VISIBLE))
    val DoesNotExist = doesNotExist()
}