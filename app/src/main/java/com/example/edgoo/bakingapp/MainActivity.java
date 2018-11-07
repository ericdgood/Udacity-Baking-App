package com.example.edgoo.bakingapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.edgoo.bakingapp.RecipeData.FetchRecipeData;

import butterknife.BindView;

public class MainActivity extends AppCompatActivity {

    private RecipeAdapter mRecipeAdapter;
    @BindView(R.id.recycler_view) RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        THINGS NEEDED FOR ROOM AND ADAPTER
//        recyclerView.setLayoutManager(new LinearLayoutManager(this));

//        mRecipeAdapter = new RecipeAdapter( this);
//        recyclerView.setAdapter(mRecipeAdapter);
        
        loadRecipeData();

    }

    private void loadRecipeData() {
       new FetchRecipeData(mRecipeAdapter).execute();
    }
}
