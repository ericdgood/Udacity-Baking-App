package com.example.edgoo.bakingapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

public class RecipeStepList extends AppCompatActivity {

    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mobile_recipe_step_list);

        setTitle(getIntent().getStringExtra("recipe_name"));
        ArrayList ingredients = getIntent().getStringArrayListExtra("ingredients");
        ArrayList ingredientsQty = getIntent().getStringArrayListExtra("ingredients_qty");
        ArrayList ingredientsMeasure = getIntent().getStringArrayListExtra("ingredients_measure");
        ArrayList stepId = getIntent().getStringArrayListExtra("step_id");
        ArrayList shortDescription = getIntent().getStringArrayListExtra("short_description");

//        RECYCLERVIEW FOR INGREDIENTS
        recyclerView = findViewById(R.id.recycler_view_ingred);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        RecipeIngredientsListAdapter mRecipeIngredientsListAdapter = new RecipeIngredientsListAdapter(ingredients, ingredientsQty, ingredientsMeasure);
        recyclerView.setAdapter(mRecipeIngredientsListAdapter);

//        RECYCLERVIEW FOR STEPS
        recyclerView = findViewById(R.id.recipe_steps_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        RecipeStepsListAdapter mRecipeStepsListAdapter = new RecipeStepsListAdapter(stepId, shortDescription);
        recyclerView.setAdapter(mRecipeStepsListAdapter);
    }

}
