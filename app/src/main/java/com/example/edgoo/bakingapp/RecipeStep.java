package com.example.edgoo.bakingapp;

import android.content.Intent;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.edgoo.bakingapp.Fragments.StepVideoDescripFrag;
import com.example.edgoo.bakingapp.Fragments.StepsListFragment;
import com.google.android.exoplayer2.DefaultLoadControl;
import com.google.android.exoplayer2.ExoPlaybackException;
import com.google.android.exoplayer2.ExoPlayer;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.LoadControl;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.Timeline;
import com.google.android.exoplayer2.extractor.DefaultExtractorsFactory;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.source.TrackGroupArray;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.trackselection.TrackSelectionArray;
import com.google.android.exoplayer2.trackselection.TrackSelector;
import com.google.android.exoplayer2.ui.SimpleExoPlayerView;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.util.Util;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.example.edgoo.bakingapp.RecipeAdapter.thumbUrl;
import static com.example.edgoo.bakingapp.RecipeAdapter.videoUrl;

public class RecipeStep extends AppCompatActivity {

    @BindView(R.id.previous_step)
    ImageButton previousStep;
    @BindView(R.id.next_step)
    ImageButton nextStep;
    int currentStepDisplay;
    int arrayListSize;
    private SimpleExoPlayer mExoPlayer;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//          DO THIS IF VERTICAL.
        if (this.getResources().getConfiguration().orientation !=
                Configuration.ORIENTATION_LANDSCAPE) {

            setContentView(R.layout.recipe_step);
            ButterKnife.bind(this);

            final ArrayList videoUrl = RecipeAdapter.videoUrl;
            final ArrayList thumbUrl = RecipeAdapter.thumbUrl;
            final ArrayList descriptionsArray = RecipeAdapter.description;
            arrayListSize = descriptionsArray.size() - 1;
            int step_id = Integer.parseInt(getIntent().getStringExtra("step_id"));
            currentStepDisplay = step_id;
            setTitle("Step " + String.valueOf(step_id));

            StepVideoDescripFrag stepVideoFragment = new StepVideoDescripFrag();
            FragmentManager fragmentManager = getSupportFragmentManager();
            stepVideoFragment.VideoFragPass(this, videoUrl, thumbUrl, step_id);
            fragmentManager.beginTransaction()
                    .add(R.id.step_video_descrip_frag, stepVideoFragment)
                    .commit();

            nextStep.setOnClickListener(v -> {
                if (currentStepDisplay < arrayListSize) {
                    currentStepDisplay = currentStepDisplay + 1;

//                    mExoPlayer.stop();
                    Intent nextStepIntent = new Intent(RecipeStep.this, RecipeStep.class);
                    nextStepIntent.putExtra("step_id", String.valueOf(currentStepDisplay));
                    finish();
                    startActivity(nextStepIntent);
                }
            });
//
//            previousStep.setOnClickListener(v -> {
//                if (currentStepDisplay >= 1) {
//                    currentStepDisplay = currentStepDisplay - 1;
//                    description.setText((CharSequence) descriptionsArray.get(currentStepDisplay));
//
//                    mExoPlayer.stop();
//                    Intent nextStepIntent = new Intent(RecipeStep.this, RecipeStep.class);
//                    nextStepIntent.putExtra("step_id", String.valueOf(currentStepDisplay));
//                    finish();
//                    startActivity(nextStepIntent);
//                }
//            });
        }
//            DO THIS IF ORIENTATION IS LANDSCAPE
        else {
            setContentView(R.layout.recipe_step);
            final ArrayList videoUrl = RecipeAdapter.videoUrl;
            final ArrayList thumbUrl = RecipeAdapter.thumbUrl;
            int step_id = Integer.parseInt(getIntent().getStringExtra("step_id"));
            setTitle("Step " + String.valueOf(step_id));

            StepVideoDescripFrag stepVideoFragment = new StepVideoDescripFrag();
            FragmentManager fragmentManager = getSupportFragmentManager();
            stepVideoFragment.VideoFragPass(this, videoUrl, thumbUrl, step_id);
            fragmentManager.beginTransaction()
                    .add(R.id.recipe_step_list_frag, stepVideoFragment)
                    .commit();

        }
    }


}

