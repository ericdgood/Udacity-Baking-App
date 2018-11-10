package com.example.edgoo.bakingapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.example.edgoo.bakingapp.RecipeData.Recipes;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

import static android.content.ContentValues.TAG;

public class RecipeStepList extends AppCompatActivity {

    @BindView(R.id.recycler_view_ingred) RecyclerView recyclerView;
    @BindView(R.id.recipe_steps_list) RecyclerView recipeStepListRecycler;

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

//        RECYCLERVIEW FOR STEPS
        recipeStepListRecycler.setLayoutManager(new LinearLayoutManager(this));

        RecipeStepsListAdapter mRecipeStepsListAdapter = new RecipeStepsListAdapter(this, stepId, shortDescription);
        recipeStepListRecycler.setAdapter(mRecipeStepsListAdapter);
    }

}
