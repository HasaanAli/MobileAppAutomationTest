package com.mytaxi.android_demo.poms

import android.support.design.widget.Snackbar
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import com.mytaxi.android_demo.utils.LogT
import org.hamcrest.CoreMatchers.allOf

interface SnackBarNotification {
    /**
     * Verifies given snackBarText is displayed, then waits 2 seconds for snackbar to hide
     */
    fun verifySnackBarNotification(snackBarText: SnackBarText) {
        LogT.i("verify snackbar is displayed '${snackBarText.text}'")
        onView(withText(snackBarText.text))
                .check(matches(allOf(
                        isDescendantOfA(isAssignableFrom(Snackbar.SnackbarLayout::class.java)),
                        isCompletelyDisplayed()
                )))
        Thread.sleep(2000)
    }
}

enum class SnackBarText(val text: String) {
    // Login screen
    LoginFailed("Login failed")
}
