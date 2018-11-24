package com.example.edgoo.bakingapp;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

import com.example.edgoo.bakingapp.Fragments.StepDetailFragment;
import com.example.edgoo.bakingapp.Fragments.StepsListFragment;
import com.example.edgoo.bakingapp.RecipeData.Recipes;

public class StepDetailActivity extends AppCompatActivity {

    private int currentStepDisplay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_step_detail);

        if ((getIntent().getStringExtra("step_id")) != null) {
            currentStepDisplay = Integer.parseInt(getIntent().getStringExtra("step_id"));
        } else {
            currentStepDisplay =0;
        }

        StepDetailFragment stepDetailFragment = new StepDetailFragment();
        stepDetailFragment.setStepsModel(currentStepDisplay);
        getSupportFragmentManager().beginTransaction()
                .add(R.id.step_detail_container, stepDetailFragment)
                .commit();

        if (findViewById(R.id.recipe_step_list_frag) != null) {

            StepsListFragment stepsFragment = new StepsListFragment();
            FragmentManager fragmentIngredManager = getSupportFragmentManager();
            stepsFragment.StepsList(this);
            fragmentIngredManager.beginTransaction()
                    .replace(R.id.recipe_step_list_frag, stepsFragment)
                    .commit();
        }
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        if(newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            getSupportActionBar().hide();
        } else {
            getSupportActionBar().show();
        }
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        if (findViewById(R.id.recipe_step_list_frag) != null){
            Intent back = new Intent(this, MainActivity.class);
            startActivity(back);
        } else {
            Intent back = new Intent(this, RecipeStepList.class);
            startActivity(back);
        }
    }
}
