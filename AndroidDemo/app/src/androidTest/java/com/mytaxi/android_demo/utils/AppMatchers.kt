package com.mytaxi.android_demo.utils

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.ViewInteraction
import androidx.test.espresso.matcher.ViewMatchers.*
import com.mytaxi.android_demo.R
import org.hamcrest.CoreMatchers.allOf

/**
 * Util class containing common matcher for MyTaxi app
 */
object AppMatchers {
    val toolBarParentMatcher = withParent(withId(R.id.toolbar))

    fun getToolBarTextViewInteraction(toolbarText: String) : ViewInteraction {
        return onView(allOf(withText(toolbarText), toolBarParentMatcher))
    }

}
