package com.example.edgoo.bakingapp;

import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.Random;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

@RunWith(AndroidJUnit4.class)
public class BakingAppTesting {

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
}
