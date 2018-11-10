package com.example.edgoo.bakingapp;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.edgoo.bakingapp.RecipeData.Recipes;

import java.util.ArrayList;
import java.util.Arrays;

import butterknife.BindView;
import butterknife.ButterKnife;

import static android.content.ContentValues.TAG;

public class RecipeStep extends AppCompatActivity {

    @BindView(R.id.step_description) TextView description;
    @BindView(R.id.previous_step) ImageButton previousStep;
    @BindView(R.id.next_step) ImageButton nextStep;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recipe_step);
        ButterKnife.bind(this);

        ArrayList descriptionsArray = getIntent().getStringArrayListExtra("description");
        String step_id = getIntent().getStringExtra("step_id");

//        GETS STEP FROM ARRAY WITH STEPID AND DISPLAYS
        description.setText((CharSequence) descriptionsArray.get(Integer.parseInt(step_id)));

    }
}
