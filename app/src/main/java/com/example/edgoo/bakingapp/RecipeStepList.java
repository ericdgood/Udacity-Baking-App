package com.example.edgoo.bakingapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.edgoo.bakingapp.Fragments.StepVideoDescripFrag;
import com.example.edgoo.bakingapp.Fragments.StepsListFragment;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RecipeStepList extends AppCompatActivity {

    @BindView(R.id.recycler_view_ingred) RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mobile_recipe_step_list);

        setTitle(RecipeAdapter.recipeName);
        ArrayList ingredients = getIntent().getStringArrayListExtra("ingredients");
        ArrayList ingredientsQty = getIntent().getStringArrayListExtra("ingredients_qty");
        ArrayList ingredientsMeasure = getIntent().getStringArrayListExtra("ingredients_measure");

//        TABLET LAYOUT
        if (findViewById(R.id.mobile_step_list) == null) {

            StepVideoDescripFrag stepVideoFragment = new StepVideoDescripFrag();
            FragmentManager fragmentVideoManager = getSupportFragmentManager();
            stepVideoFragment.VideoFragPass(this);
            fragmentVideoManager.beginTransaction()
                    .replace(R.id.step_video_descrip_frag, stepVideoFragment)
                    .commit();

            StepsListFragment stepsFragment = new StepsListFragment();
            FragmentManager fragmentManager = getSupportFragmentManager();
            stepsFragment.StepsList(this);
            fragmentManager.beginTransaction()
                    .replace(R.id.recipe_step_list_frag, stepsFragment)
                    .commit();

        } else {

            ButterKnife.bind(this);
//        RECYCLERVIEW FOR INGREDIENTS
            recyclerView.setLayoutManager(new LinearLayoutManager(this));

            RecipeIngredientsListAdapter mRecipeIngredientsListAdapter = new RecipeIngredientsListAdapter(ingredients, ingredientsQty, ingredientsMeasure);
            recyclerView.setAdapter(mRecipeIngredientsListAdapter);

            // CREATES A NEW FRAGMENT FOR STEPS LIST
            StepsListFragment stepsFragment = new StepsListFragment();

            // Add the fragment to its container using a FragmentManager and a Transaction
            FragmentManager fragmentManager = getSupportFragmentManager();

            stepsFragment.StepsList(this);
            fragmentManager.beginTransaction()
                    .add(R.id.recipe_step_list_frag, stepsFragment)
                    .commit();

        }
    }

    @Override
    public void onBackPressed() {

        finish();
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

}
