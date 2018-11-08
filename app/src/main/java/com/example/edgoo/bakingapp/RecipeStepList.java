package com.example.edgoo.bakingapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.edgoo.bakingapp.RecipeData.FetchRecipeData;
import com.example.edgoo.bakingapp.RecipeData.Recipes;

public class RecipeStepList extends AppCompatActivity {

    Recipes[] mRecipes;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mobile_recipe_step_list_layout);


        setTitle(getIntent().getStringExtra("recipe_name"));

//        THINGS NEEDED FOR ROOM AND ADAPTER
        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        RecipeStepListAdapter mRecipeAdapter = new RecipeStepListAdapter(mRecipes);
        recyclerView.setAdapter(mRecipeAdapter);

    }

}
