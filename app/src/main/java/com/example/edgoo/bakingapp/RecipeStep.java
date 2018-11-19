package com.example.edgoo.bakingapp;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

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

public class RecipeStep extends AppCompatActivity implements ExoPlayer.EventListener{

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

        final ArrayList descriptionsArray = RecipeAdapter.description;
        final ArrayList mVideoUrl = RecipeAdapter.videoUrl;
        final ArrayList mThumbUrl = RecipeAdapter.thumbUrl;
        currentStepDisplay = Integer.parseInt(getIntent().getStringExtra("step_id"));

//          DO THIS IF VERTICAL.
        if (this.getResources().getConfiguration().orientation !=
                Configuration.ORIENTATION_LANDSCAPE) {

            setContentView(R.layout.recipe_step);
            ButterKnife.bind(this);

            arrayListSize = descriptionsArray.size() - 1;
            setTitle("Step " + String.valueOf(currentStepDisplay));

            TextView descrip = findViewById(R.id.step_description);
            descrip.setText((CharSequence) descriptionsArray.get(currentStepDisplay));
            getURL(mVideoUrl, mThumbUrl,currentStepDisplay);

            nextStep.setOnClickListener(v -> {
                if (currentStepDisplay < arrayListSize) {
                    currentStepDisplay = currentStepDisplay + 1 ;
                    setTitle("Step " + String.valueOf(currentStepDisplay));
                    mExoPlayer.stop();
                    Intent nextStep = new Intent(RecipeStep.this, RecipeStep.class);
                    nextStep.putExtra("step_id", String.valueOf(currentStepDisplay));
                    finish();
                    startActivity(nextStep);
                }
            });

            previousStep.setOnClickListener(v -> {
                if (currentStepDisplay >= 1) {
                    currentStepDisplay = currentStepDisplay - 1 ;
                    setTitle("Step " + String.valueOf(currentStepDisplay));
                    mExoPlayer.stop();
                    Intent nextStep = new Intent(RecipeStep.this, RecipeStep.class);
                    nextStep.putExtra("step_id", String.valueOf(currentStepDisplay));
                    startActivity(nextStep);
                }
            });
        }
//            DO THIS IF MOBILE ORIENTATION IS LANDSCAPE
        else if (findViewById(R.id.mobile_landscape) == null){
            setContentView(R.layout.recipe_step);
            setTitle("Step " + String.valueOf(currentStepDisplay));

            TextView descrip = findViewById(R.id.step_description);
            descrip.setText((CharSequence) descriptionsArray.get(currentStepDisplay));
            getURL(mVideoUrl, mThumbUrl,currentStepDisplay);

        }
//        DO THIS IF TABLET
        else {
            setContentView(R.layout.recipe_step);
            setTitle("Step " + String.valueOf(currentStepDisplay));

            TextView descrip = findViewById(R.id.step_description);
            descrip.setText((CharSequence) descriptionsArray.get(currentStepDisplay));
            getURL(mVideoUrl, mThumbUrl,currentStepDisplay);

            StepsListFragment stepsFragment = new StepsListFragment();
            FragmentManager fragmentIngredManager = getSupportFragmentManager();
            stepsFragment.StepsList(this);
            fragmentIngredManager.beginTransaction()
                    .replace(R.id.recipe_step_list_frag, stepsFragment)
                    .commit();
        }
    }

    private void initializePlayer(Uri mediaUri) {
        SimpleExoPlayerView mPlayerView = findViewById(R.id.playerView);
        if (mExoPlayer == null) {
            // Create an instance of the ExoPlayer.
            TrackSelector trackSelector = new DefaultTrackSelector();
            LoadControl loadControl = new DefaultLoadControl();
            mExoPlayer = ExoPlayerFactory.newSimpleInstance(this, trackSelector, loadControl);
            mPlayerView.setPlayer(mExoPlayer);

            // Set the ExoPlayer.EventListener to this activity.
            mExoPlayer.addListener(this);

            // Prepare the MediaSource.
            String userAgent = Util.getUserAgent(this, "bakingApp");
            MediaSource mediaSource = new ExtractorMediaSource(mediaUri, new DefaultDataSourceFactory(
                    this, userAgent), new DefaultExtractorsFactory(), null, null);
            mExoPlayer.prepare(mediaSource);
            mExoPlayer.setPlayWhenReady(true);
        }
    }

    private void getURL (ArrayList videoUrl, ArrayList thumbUrl,int step_id){
        //        GETS VIDEO AND SENDS TO EXO PLAYER
        if (!String.valueOf(videoUrl.get(step_id)).equals("")) {
            initializePlayer(Uri.parse(String.valueOf(videoUrl.get(step_id))));
        } else if (!String.valueOf(thumbUrl.get(step_id)).equals("")) {
            initializePlayer(Uri.parse(String.valueOf(thumbUrl.get(step_id))));
        } else {
            RelativeLayout noVideo = findViewById(R.id.no_video);
            SimpleExoPlayerView mPlayerView = findViewById(R.id.playerView);
            noVideo.setVisibility(View.VISIBLE);
            initializePlayer(Uri.parse(""));
            mPlayerView.setVisibility(View.INVISIBLE);
        }
    }

    @Override
    public void onTimelineChanged(Timeline timeline, Object manifest) {

    }

    @Override
    public void onTracksChanged(TrackGroupArray trackGroups, TrackSelectionArray trackSelections) {

    }

    @Override
    public void onLoadingChanged(boolean isLoading) {

    }

    @Override
    public void onPlayerStateChanged(boolean playWhenReady, int playbackState) {

    }

    @Override
    public void onPlayerError(ExoPlaybackException error) {

    }

    @Override
    public void onPositionDiscontinuity() {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mExoPlayer.stop();
    }
}

