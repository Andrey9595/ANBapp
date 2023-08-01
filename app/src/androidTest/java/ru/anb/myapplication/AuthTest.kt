package ru.anb.myapplication

import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.*
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import ru.anb.myapplication.core.App
import ru.anb.myapplication.core.ui.MainActivity

@RunWith(AndroidJUnit4::class)
class AuthTest {

    @get: Rule
    val rule = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun inputTestText() {
     onView(withId(R.id.login_edittext)).perform(click())
        onView(withId(R.id.login_edittext)).perform(typeText("check"))
        onView(withId(R.id.login_edittext)).check(matches(withText("check")))
    }

    @Test
    fun clickSignIn(){

        val context = ApplicationProvider.getApplicationContext<App>()

        onView(withId(R.id.sign_in)).perform(click())
        onView(withId(R.id.auth_login)).check(matches(withErrorInInputLayout(context.getString(R.string.login_error))))
        onView(withId(R.id.auth_password)).check(matches(withErrorInInputLayout(context.getString(R.string.password_length_error))))
    }

    @Test
    fun transitionTest(){
        onView(withId(R.id.navigate_to_sign_up)).perform(click())
        onView(withId(R.id.register_fragment)).check(matches(isDisplayed()))
    }
}