package com.example.edgoo.bakingapp;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

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
        ButterKnife.bind(this);

        setTitle(getIntent().getStringExtra("recipe_name"));
        ArrayList ingredients = getIntent().getStringArrayListExtra("ingredients");
        ArrayList ingredientsQty = getIntent().getStringArrayListExtra("ingredients_qty");
        ArrayList ingredientsMeasure = getIntent().getStringArrayListExtra("ingredients_measure");
        ArrayList stepId = getIntent().getStringArrayListExtra("step_id");
        ArrayList shortDescription = getIntent().getStringArrayListExtra("short_description");

//        RECYCLERVIEW FOR INGREDIENTS
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        RecipeIngredientsListAdapter mRecipeIngredientsListAdapter = new RecipeIngredientsListAdapter(ingredients, ingredientsQty, ingredientsMeasure);
        recyclerView.setAdapter(mRecipeIngredientsListAdapter);

        // CREATES A NEW FRAGMENT FOR STEPS LIST
        StepsListFragment stepsFragment = new StepsListFragment();

        // Add the fragment to its container using a FragmentManager and a Transaction
        FragmentManager fragmentManager = getSupportFragmentManager();

        stepsFragment.StepsList(this, stepId, shortDescription);
        fragmentManager.beginTransaction()
                .add(R.id.recipe_step_list_frag, stepsFragment)
                .commit();

    }

}
