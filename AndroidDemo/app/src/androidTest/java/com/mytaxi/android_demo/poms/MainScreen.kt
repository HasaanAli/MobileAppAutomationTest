package com.mytaxi.android_demo.poms

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.RootMatchers
import androidx.test.espresso.matcher.ViewMatchers.*
import com.mytaxi.android_demo.R
import com.mytaxi.android_demo.utils.AppMatchers
import com.mytaxi.android_demo.utils.LogT
import org.hamcrest.CoreMatchers.allOf

class MainScreen : Screen {
    val myTaxiToolBar = AppMatchers.getToolBarTextViewInteraction("mytaxi demo")
    val drawerButton = onView(allOf(withContentDescription("Open navigation drawer"), AppMatchers.toolBarParentMatcher))
    val searchField = onView(withId(R.id.textSearch))

    override fun verifyIsDisplayed() {
        myTaxiToolBar.check(matches(isCompletelyDisplayed()))
        drawerButton.check(matches(isCompletelyDisplayed()))
        searchField.check(matches(isCompletelyDisplayed()))
    }

    fun searchDriver(text: String) {
        LogT.i("search driver '${text}' on main screen")
        searchField.perform(click(), clearText(), typeText(text)) //perform user-like actions instead of replaceText()
    }

    fun clickSearchResult(text: String) {
        LogT.i("click driver search result '${text}'")
        onView(withText(text))
                .inRoot(RootMatchers.isPlatformPopup())
                .perform(click())
    }
}