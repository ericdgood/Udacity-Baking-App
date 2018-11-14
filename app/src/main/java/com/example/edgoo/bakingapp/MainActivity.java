package com.example.edgoo.bakingapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.GridView;
import android.widget.ListAdapter;
import android.widget.TextView;

import com.example.edgoo.bakingapp.RecipeData.FetchRecipeData;
import com.example.edgoo.bakingapp.RecipeData.Recipes;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    private RecipeAdapter mRecipeAdapter;
    private RecipeAdapterTab mAdapterGrid;
    Recipes[] mRecipes;
    @BindView(R.id.recycler_view) RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        ButterKnife.bind(this);


        if (findViewById(R.id.main_gridview) != null) {
//            GridView gridView = (GridView) findViewById(R.id.main_gridview);
            RecyclerView recyclerView = findViewById(R.id.recycler_view);
            GridLayoutManager glm = new GridLayoutManager(this, 2);
            recyclerView.setLayoutManager(glm);
            mRecipeAdapter = new RecipeAdapter(mRecipes, this);
//            mAdapterGrid = new RecipeAdapterTab(mRecipes, this);
            recyclerView.setAdapter(mRecipeAdapter);
            loadRecipeData();
        } else {

            ButterKnife.bind(this);
//        THINGS NEEDED FOR ROOM AND ADAPTER
            recyclerView.setLayoutManager(new LinearLayoutManager(this));

            mRecipeAdapter = new RecipeAdapter(mRecipes, this);
            recyclerView.setAdapter(mRecipeAdapter);

            loadRecipeData();

        }
    }

    private void loadRecipeData() {
        new FetchRecipeData(mRecipeAdapter).execute();
    }
}
