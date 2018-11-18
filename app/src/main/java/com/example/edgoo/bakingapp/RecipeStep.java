package com.example.edgoo.bakingapp;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageButton;

import com.example.edgoo.bakingapp.Fragments.StepVideoDescripFrag;
import com.google.android.exoplayer2.DefaultLoadControl;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.LoadControl;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.trackselection.TrackSelector;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.example.edgoo.bakingapp.RecipeAdapter.videoUrl;

public class RecipeStep extends AppCompatActivity {

    @BindView(R.id.previous_step)
    ImageButton previousStep;
    @BindView(R.id.next_step)
    ImageButton nextStep;
    int currentStepDisplay;
    int arrayListSize;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//          DO THIS IF VERTICAL.
        if (this.getResources().getConfiguration().orientation !=
                Configuration.ORIENTATION_LANDSCAPE) {

            setContentView(R.layout.recipe_step);
            ButterKnife.bind(this);

//            final ArrayList videoUrl = RecipeAdapter.videoUrl;
//            final ArrayList thumbUrl = RecipeAdapter.thumbUrl;
            final ArrayList descriptionsArray = RecipeAdapter.description;
            arrayListSize = descriptionsArray.size() - 1;
            currentStepDisplay = Integer.parseInt(getIntent().getStringExtra("step_id"));
            setTitle("Step " + String.valueOf(currentStepDisplay));

            StepVideoDescripFrag stepVideoFragment = new StepVideoDescripFrag();
            FragmentManager fragmentManager = getSupportFragmentManager();
            stepVideoFragment.VideoFragPass(this, currentStepDisplay);
            fragmentManager.beginTransaction()
                    .add(R.id.step_video_descrip_frag, stepVideoFragment)
                    .commit();

            nextStep.setOnClickListener(v -> {
                if (currentStepDisplay < arrayListSize) {
                    currentStepDisplay = currentStepDisplay + 1;
                    setTitle("Step " + String.valueOf(currentStepDisplay));
                    stepVideoFragment.mExoPlayer.stop();
                    StepVideoDescripFrag nextStepVideoFragment = new StepVideoDescripFrag();
                    nextStepVideoFragment.VideoFragPass(this, currentStepDisplay);
                    fragmentManager.beginTransaction()
                            .replace(R.id.step_video_descrip_frag, nextStepVideoFragment)
                            .commit();
                }
            });

            previousStep.setOnClickListener(v -> {
                if (currentStepDisplay >= 1) {
                    currentStepDisplay = currentStepDisplay - 1;
                    setTitle("Step " + String.valueOf(currentStepDisplay));
                    stepVideoFragment.mExoPlayer.stop();
                    StepVideoDescripFrag nextStepVideoFragment = new StepVideoDescripFrag();
                    nextStepVideoFragment.VideoFragPass(this, currentStepDisplay);
                    fragmentManager.beginTransaction()
                            .replace(R.id.step_video_descrip_frag, nextStepVideoFragment)
                            .commit();
                }
            });
        }
//            DO THIS IF ORIENTATION IS LANDSCAPE
        else {
            setContentView(R.layout.recipe_step);
            currentStepDisplay = Integer.parseInt(getIntent().getStringExtra("step_id"));
            setTitle("Step " + String.valueOf(currentStepDisplay));

            StepVideoDescripFrag landVideoFragment = new StepVideoDescripFrag();
            FragmentManager fragmentManager = getSupportFragmentManager();
            landVideoFragment.VideoFragPass(this, currentStepDisplay);
            fragmentManager.beginTransaction()
                    .add(R.id.step_video_descrip_frag, landVideoFragment)
                    .commit();

        }
    }


}

