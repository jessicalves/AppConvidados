package com.jessmobilesolutions.appconvidados

import androidx.test.core.app.ActivityScenario.launch
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.clearText
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.jessmobilesolutions.appconvidados.view.GuestFormActivity
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class GuestFormActivityTest {

    @Test
    fun viewsExists() {
        launch(GuestFormActivity::class.java)
        onView(withId(R.id.edit_nome)).check(matches(isDisplayed()))
        onView(withId(R.id.radio_confimation)).check(matches(isDisplayed()))
        onView(withId(R.id.button_save)).check(matches(isDisplayed()))
    }

    @Test
    fun testSave() {
        launch(GuestFormActivity::class.java)
        onView(withId(R.id.edit_nome)).perform(clearText(), typeText("Convidado Teste 2"))
        onView(withId(R.id.button_save)).perform(click())
    }
}