package com.example.edgoo.bakingapp;

import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.widget.TextView;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.contrib.RecyclerViewActions.scrollToPosition;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.Matchers.allOf;

@RunWith(AndroidJUnit4.class)
public class BakingAppMainActivityTesting {

    private static final String RECIPE_NAME_NUTELLA_PIE = "Nutella Pie";
    private static final String RECIPE_NAME_BROWNIES = "Brownies";
    private static final String RECIPE_NAME_YELLOW_CAKE = "Yellow Cake";
    private static final String RECIPE_NAME_CHEESECAKE = "Cheesecake";
    private static final String SAMPLE_INGREDIENT = "Graham Cracker crumbs";

    @Rule
    public ActivityTestRule<MainActivity> mRecipeStepTestRule = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void checkIngredientsLabel(){
        onView(withId(R.id.recycler_view)).perform(scrollToPosition(0));
        onView(withText(RECIPE_NAME_NUTELLA_PIE)).check(matches(isDisplayed()));
        onView(withId(R.id.recycler_view)).perform(scrollToPosition(1));
        onView(withText(RECIPE_NAME_BROWNIES)).check(matches(isDisplayed()));
        onView(withId(R.id.recycler_view)).perform(scrollToPosition(2));
        onView(withText(RECIPE_NAME_YELLOW_CAKE)).check(matches(isDisplayed()));
        onView(withId(R.id.recycler_view )).perform(scrollToPosition(3));
        onView(withText(RECIPE_NAME_CHEESECAKE)).check(matches(isDisplayed()));
    }

    @Test
    public void clickRecipeRecyclerViewItem_OpensRecipeDetailActivity() {
        onView(withId(R.id.recycler_view)).perform(RecyclerViewActions.actionOnItemAtPosition(0, click()));
        onView(allOf(instanceOf(TextView.class), withText(RECIPE_NAME_NUTELLA_PIE))).check(matches(isDisplayed()));
        onView(withId(R.id.recycler_view)).perform(scrollToPosition(0));
    }
}
