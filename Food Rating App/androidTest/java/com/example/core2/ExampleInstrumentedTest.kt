package com.example.core2

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Rule

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {

    @Rule
    @JvmField
    val rule: ActivityTestRule<MainActivity> = ActivityTestRule(MainActivity::class.java)

    @Test
    fun userCanClickAnImageCompareAndEditAnInput() {

        //Click the Butter Chicken Image
        onView(withId(R.id.imageButterChicken)).perform(click())

        //Check that the name field is equal to Butter Chicken
        onView(withId(R.id.nameInput)).check(matches(withText("Butter Chicken")))

        //Delete the text in the name field
        onView(withId(R.id.nameInput)).perform(clearText())

        //Write a new name for the name field
        onView(withId(R.id.nameInput)).perform(typeText("Indian Chicken"))
    }
}