package com.nanodegree.sam.baker;


import android.app.Fragment;
import android.support.test.espresso.Espresso;
import android.support.test.espresso.IdlingRegistry;
import android.support.test.espresso.IdlingResource;
import android.support.test.espresso.idling.CountingIdlingResource;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.android21buttons.fragmenttestrule.FragmentTestRule;
import com.jakewharton.espresso.OkHttp3IdlingResource;
import com.nanodegree.sam.baker.project.activities.MainActivity;
import com.nanodegree.sam.baker.project.fragments.FoodFragment;
import com.nanodegree.sam.baker.project.network.ApiFactory;
import com.nanodegree.sam.baker.project.network.FoodNetworkService;

import org.junit.Before;
import org.junit.Rule;
import org.junit.runner.RunWith;
import org.junit.Rule;
import org.junit.Test;

import okhttp3.OkHttpClient;

import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.anything;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsInstanceOf.instanceOf;


@RunWith(AndroidJUnit4.class)

public class MainActivityBasicTest {

    @Rule
    public ActivityTestRule<MainActivity> mainActivityActivityTestRule
            = new ActivityTestRule<MainActivity>(MainActivity.class);

    @Before
    public void init(){

        mainActivityActivityTestRule.getActivity()
                .getSupportFragmentManager()
                .beginTransaction();

    }

    @Test
    public void test_that_main_fragment_container_is_displayed() {
       onView(withId(R.id.food_names_container)).check(matches(isDisplayed()));
    }

    @Test
    public void test_that_recycler_view_is_added_to_the_fragment() {
        onView(withId(R.id.rv_food_names)).check(matches(isDisplayed()));
    }

    @Test
    public void test_that_clicking_recycler_view_opens_ingredient_fragment()
             {
                 onView(withId(R.id.rv_food_names)).perform(click());
    }


}
