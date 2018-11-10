package com.example.edgoo.bakingapp;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import com.example.edgoo.bakingapp.RecipeData.Recipes;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

import static android.content.ContentValues.TAG;

public class RecipeStep extends AppCompatActivity {

    @BindView(R.id.step_description) TextView description;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recipe_step);
        ButterKnife.bind(this);

        description.setText(getIntent().getStringExtra("description"));
    }
}
