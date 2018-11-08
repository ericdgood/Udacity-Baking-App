package com.example.edgoo.bakingapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.edgoo.bakingapp.RecipeData.FetchRecipeData;
import com.example.edgoo.bakingapp.RecipeData.Recipes;

import butterknife.BindView;

public class MainActivity extends AppCompatActivity {

    private RecipeAdapter mRecipeAdapter;
    Recipes[] mRecipes;
//    @BindView(R.id.recycler_view) RecyclerView recyclerView;
RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        THINGS NEEDED FOR ROOM AND ADAPTER
        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        mRecipeAdapter = new RecipeAdapter( mRecipes,this);
        recyclerView.setAdapter(mRecipeAdapter);
        
        loadRecipeData();

    }

    private void loadRecipeData() {
       new FetchRecipeData(mRecipeAdapter).execute();
    }
}
