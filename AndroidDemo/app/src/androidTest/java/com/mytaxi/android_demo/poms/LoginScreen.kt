package com.mytaxi.android_demo.poms

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.replaceText
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import com.mytaxi.android_demo.R
import com.mytaxi.android_demo.utils.LogT
import org.hamcrest.CoreMatchers.allOf


class LoginScreen : Screen {
    val loginActionBar = onView(allOf(withText("Login"), withParent(withId(R.id.action_bar))))
    val usernameField = onView(withId(R.id.edt_username))
    val passwordField = onView(withId(R.id.edt_password))
    val loginButton = onView(withId(R.id.btn_login))

    override fun verifyIsDisplayed() {
        loginActionBar.check(matches(isDisplayed()))
        usernameField.check(matches(isDisplayed()))
        passwordField.check(matches(isDisplayed()))
        loginButton.check(matches(isDisplayed()))
    }

    fun enterUsername(username: String) {
        LogT.i("enter username '${username}' on login screen")
        usernameField.perform(replaceText(username))
    }

    fun enterPassword(password: String) {
        LogT.i("enter password '${password}' on login screen")
        passwordField.perform(replaceText(password))
    }

    fun clickLogin() {
        LogT.i("click login button")
        loginButton.perform(click())
    }

    fun performLogin(username: String, password: String) {
        enterUsername(username)
        enterPassword(password)
        clickLogin()
    }
}