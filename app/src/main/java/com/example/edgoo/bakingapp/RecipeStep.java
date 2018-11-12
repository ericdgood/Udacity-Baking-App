package com.example.edgoo.bakingapp;

import android.content.Intent;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
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

public class RecipeStep extends AppCompatActivity implements ExoPlayer.EventListener {

    @BindView(R.id.step_description)
    TextView description;
    @BindView(R.id.previous_step)
    ImageButton previousStep;
    @BindView(R.id.next_step)
    ImageButton nextStep;
    @BindView(R.id.no_video)
    RelativeLayout noVideo;
    int currentStepDisplay;
    int arrayListSize;
    //    EXOPLAYER
    private SimpleExoPlayer mExoPlayer;
    @BindView(R.id.playerView)
    SimpleExoPlayerView mPlayerView;
    private String TAG;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//          CHECK IF ORIENTATION IS VERTICAL.
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
            getURL(videoUrl, thumbUrl, step_id);

//        GETS STEP FROM ARRAY WITH STEPID AND DISPLAYS
            description.setText((CharSequence) descriptionsArray.get(step_id));

            nextStep.setOnClickListener(v -> {
                if (currentStepDisplay < arrayListSize) {
                    currentStepDisplay = currentStepDisplay + 1;
                    description.setText((CharSequence) descriptionsArray.get(currentStepDisplay));

                    mExoPlayer.stop();
                    Intent nextStepIntent = new Intent(RecipeStep.this, RecipeStep.class);
                    nextStepIntent.putExtra("step_id", String.valueOf(currentStepDisplay));
                    finish();
                    startActivity(nextStepIntent);
                }
            });

            previousStep.setOnClickListener(v -> {
                if (currentStepDisplay >= 1) {
                    currentStepDisplay = currentStepDisplay - 1;
                    description.setText((CharSequence) descriptionsArray.get(currentStepDisplay));

                    mExoPlayer.stop();
                    Intent nextStepIntent = new Intent(RecipeStep.this, RecipeStep.class);
                    nextStepIntent.putExtra("step_id", String.valueOf(currentStepDisplay));
                    finish();
                    startActivity(nextStepIntent);
                }
            });
        }
//            DO THIS IF ORIENTATION IS LANDSCAPE
        else {
            setContentView(R.layout.recipe_step);
            final ArrayList videoUrl = RecipeAdapter.videoUrl;
            final ArrayList thumbUrl = RecipeAdapter.thumbUrl;
            int step_id = Integer.parseInt(getIntent().getStringExtra("step_id"));
            setTitle("Step " + String.valueOf(step_id));

            getURL(videoUrl, thumbUrl, step_id);
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

        private void initializePlayer (Uri mediaUri){
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
                String userAgent = Util.getUserAgent(this, "ClassicalMusicQuiz");
                MediaSource mediaSource = new ExtractorMediaSource(mediaUri, new DefaultDataSourceFactory(
                        this, userAgent), new DefaultExtractorsFactory(), null, null);
                mExoPlayer.prepare(mediaSource);
                mExoPlayer.setPlayWhenReady(true);
            }
        }

        @Override
        public void onTimelineChanged (Timeline timeline, Object manifest){
//      LEAVE BLANK
        }

        @Override
        public void onTracksChanged (TrackGroupArray trackGroups, TrackSelectionArray
        trackSelections){
//      LEAVE BLANK
        }

        @Override
        public void onLoadingChanged ( boolean isLoading){
//      LEAVE BLANK
        }

        @Override
        public void onPlayerStateChanged ( boolean playWhenReady, int playbackState){
        }

        @Override
        public void onPlayerError (ExoPlaybackException error){
//      LEAVE BLANK
        }

        @Override
        public void onPositionDiscontinuity () {
//      LEAVE BLANK
        }
}

