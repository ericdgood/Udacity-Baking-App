package com.example.edgoo.bakingapp;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

@RunWith(AndroidJUnit4.class)
public class BakingAppTesting {


    @Rule
    public ActivityTestRule<RecipeStepList> mRecipeStepTestRule = new ActivityTestRule<>(RecipeStepList.class);

    @Test
    public void checkIngredientsLabel(){
        onView(withId(R.id.ingred_label))
         .check(matches(withText(R.string.ingred_list_label)));
    }
}
