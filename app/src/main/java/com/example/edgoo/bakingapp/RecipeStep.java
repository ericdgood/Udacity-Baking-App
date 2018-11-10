package com.example.edgoo.bakingapp;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.edgoo.bakingapp.RecipeData.Recipes;

import java.util.ArrayList;
import java.util.Arrays;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static android.content.ContentValues.TAG;

public class RecipeStep extends AppCompatActivity {

    @BindView(R.id.step_description)
    TextView description;
    @BindView(R.id.previous_step)
    ImageButton previousStep;
    @BindView(R.id.next_step)
    ImageButton nextStep;
    int currentStepDisplay;
    int arrayListSize;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recipe_step);
        ButterKnife.bind(this);

        final ArrayList descriptionsArray = getIntent().getStringArrayListExtra("description");
        arrayListSize = descriptionsArray.size() - 1;
        int step_id = Integer.parseInt(getIntent().getStringExtra("step_id"));
        currentStepDisplay = step_id;

//        GETS STEP FROM ARRAY WITH STEPID AND DISPLAYS
        description.setText((CharSequence) descriptionsArray.get(step_id));

        nextStep.setOnClickListener(v -> {
            if (currentStepDisplay < arrayListSize) {
            currentStepDisplay = currentStepDisplay + 1;
                    description.setText((CharSequence) descriptionsArray.get(currentStepDisplay));
                }
            });

        previousStep.setOnClickListener(v -> {
            if (currentStepDisplay >= 1) {
            currentStepDisplay = currentStepDisplay - 1;
            description.setText((CharSequence) descriptionsArray.get(currentStepDisplay));
            }
        });
    }
}

