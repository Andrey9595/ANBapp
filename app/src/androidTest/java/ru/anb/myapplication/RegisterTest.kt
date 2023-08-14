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
class RegisterTest {

    @get: Rule
    val rule = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun inputTestText() {

        onView(withId(R.id.navigate_to_sign_up)).perform(click())

        val list = listOf(R.id.register_login, R.id.register_name, R.id.pass_layout_password, R.id.pass_layout_check_password)
        list.forEach(){
            onView(withId(it)).check(matches(isDisplayed()))
            onView(withId(it)).perform(typeText("check"))
            onView(withId(it)).check(matches(withText("check")))
        }
    }
    @Test
    fun transitionTest(){

        val context = ApplicationProvider.getApplicationContext<App>()

        onView(withId(R.id.navigate_to_sign_up)).perform(click())
        onView(withId(R.id.pass_layout_check_password)).perform(typeText("123"))
        onView(withId(R.id.start_sign_up)).perform(click())
        onView(withId(R.id.sign_up_login)).check(matches(withErrorInInputLayout(context.getString(R.string.login_error))))
        onView(withId(R.id.error_text)).check(matches(isDisplayed()))
        onView(withId(R.id.error_text)).check(matches(withText(context.getString(R.string.password_error))))
    }
}