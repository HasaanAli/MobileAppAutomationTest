package com.mytaxi.android_demo.tests

import androidx.test.espresso.IdlingRegistry
import androidx.test.rule.ActivityTestRule
import androidx.test.runner.AndroidJUnit4
import com.mytaxi.android_demo.activities.MainActivity
import com.mytaxi.android_demo.poms.DriverProfileScreen
import com.mytaxi.android_demo.poms.LoginScreen
import com.mytaxi.android_demo.poms.MainScreen
import com.mytaxi.android_demo.utils.Constants
import com.mytaxi.android_demo.utils.network.HttpClient
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class EspressoTests : Tests() {

    val loginScreen = LoginScreen()
    val mainScreen = MainScreen()
    val driverProfileScreen = DriverProfileScreen()

    @get:Rule // launch main activity after clearing app data
    var activityTestRule: ActivityTestRule<*> = ActivityTestRule(MainActivity::class.java, false, false)

    @Before
    override fun beforeEach() {
        super.beforeEach()
        // clear last login
        clearAppData() // so that launching main activity shows the login screen as required

        // After clicking login button, the espresso tests don't wait for the main screen to load
        // So we have added CountingIdlingResource (basic IdlingResource) to our HttpClient
        // (around mClient.newCall() usage)
        // Now, we should register that idling resource in Espresso so that Espresso waits for the
        // countingIdlingResource to become idle/zero
        IdlingRegistry.getInstance().register(HttpClient.getIdlingResource())
        activityTestRule.launchActivity(null)
    }

    // We use page object model classes.
    // It provides separation between tests and espresso by moving all espresso specific code
    // to our custom framework.
    @Test
    fun testEspressoTask() {
        val username = Constants.VALID_USERNAME
        val password = Constants.VALID_PASSWORD

        loginScreen.verifyIsDisplayed()
        loginScreen.performLogin(username, password)
        mainScreen.verifyIsDisplayed()

        mainScreen.searchDriver("sa")
        mainScreen.clickSearchResult("Sarah Scott")

        driverProfileScreen.verifyIsDisplayed()
        driverProfileScreen.verifyDriverName("Sarah Scott")

        // We can verify values on driver profile too
        driverProfileScreen.verifyDriverDate("2002-10-18") // or a method which accepts Date
        driverProfileScreen.verifyDriverLocation("6834 charles st")
        driverProfileScreen.clickCall()

        // We have moved outside the target app.
        // We cannot perform anymore app action/assertion in this test
        // For example, we cannot do like
        // driverProfileScreen.verifyIsNotDisplayed()
    }

    @After
    override fun afterEach() {
        //Anything after each test
        IdlingRegistry.getInstance().unregister(HttpClient.getIdlingResource())
        super.afterEach()
    }
}
