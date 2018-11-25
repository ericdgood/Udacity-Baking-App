package com.example.edgoo.bakingapp;

import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.util.Log;
import android.view.View;

import org.hamcrest.Matcher;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.Random;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.scrollTo;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

@RunWith(AndroidJUnit4.class)
public class BakingAppTesting {

    private static final String NUTELLA_PIE = "Nutella Pie";
    private static final String TAG = "test";

    //random number between 0-3 that we will test with,to not test always the same number.
    private final int randPosition;
    @Rule
    public ActivityTestRule<MainActivity> mMainActivityTestRule = new ActivityTestRule<>(MainActivity.class);

    public BakingAppTesting() {
        Random rand = new Random();
        randPosition = rand.nextInt(3);
    }

    /**
     * This test will check if we retrieved the network data and the image Recycler at a random possible position is clickable
     */
    @Test
    public void mainActivityBasicTest()
    {
        onView(withId(R.id.recycler_view)).perform(RecyclerViewActions.actionOnItemAtPosition(randPosition, click()));
    }

    @Test
    public void clickRecyclerViewItem_NutellaPie_OpensRecipeDetailActivity() {
        onView(ViewMatchers.withId(R.id.recycler_view)).perform(RecyclerViewActions.actionOnItemAtPosition(0, scrollTo()));
//        onView(withText(NUTELLA_PIE)).perform(click());
        onView(withId(R.id.recipe_name)).check(matches(withText(NUTELLA_PIE)));
    }
}
