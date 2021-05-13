package com.example.edgoo.bakingapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.edgoo.bakingapp.RecipeData.FetchRecipeData;
import com.example.edgoo.bakingapp.RecipeData.Recipes;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    private RecipeAdapter mRecipeAdapter;
    Recipes[] mRecipes;
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        if (findViewById(R.id.table_layout) != null) {
            recyclerView.setLayoutManager( new GridLayoutManager(this, 2));
            mRecipeAdapter = new RecipeAdapter(mRecipes, this);
            recyclerView.setAdapter(mRecipeAdapter);
            loadRecipeData();
        } else {

            recyclerView.setLayoutManager(new LinearLayoutManager(this));
            mRecipeAdapter = new RecipeAdapter(mRecipes, this);
            recyclerView.setAdapter(mRecipeAdapter);

            loadRecipeData();
// Testing pr scheduler current banner updated
        }
    }

    private void loadRecipeData() {
        new FetchRecipeData(mRecipeAdapter).execute();
        // tetsing different change
    }
}
