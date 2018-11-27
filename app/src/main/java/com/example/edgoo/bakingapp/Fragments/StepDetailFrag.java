package com.example.edgoo.bakingapp.Fragments;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.VisibleForTesting;
import android.support.v4.app.Fragment;
import android.support.v7.widget.AppCompatTextView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.edgoo.bakingapp.R;
import com.example.edgoo.bakingapp.RecipeAdapter;
import com.example.edgoo.bakingapp.StepDetailActivity;
import com.google.android.exoplayer2.DefaultLoadControl;
import com.google.android.exoplayer2.DefaultRenderersFactory;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.ui.PlayerView;
import com.google.android.exoplayer2.upstream.DefaultHttpDataSourceFactory;
import com.google.android.exoplayer2.util.Util;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class StepDetailFrag extends Fragment {

    private int mCurrentStep;
    @BindView(R.id.playerView)
        PlayerView videoPlayerView;

        @BindView(R.id.no_video)
        RelativeLayout noVideo;

        @BindView(R.id.step_description)
        TextView stepDescription;

        SimpleExoPlayer player;
        boolean playWhenReady = false;
        int currentWindow = 0;
        long playbackPosition = 0;
        private View rootView;


        public StepDetailFrag() { }

        @Nullable
        @Override
        public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

            // Create view
            rootView = inflater.inflate(R.layout.recipe_step, container, false);

            ButterKnife.bind(this, rootView);


            // Release player if it is not null
            if (player != null) {
                releasePlayer();
            }

                final ArrayList descriptionsArray = RecipeAdapter.description;
                final ArrayList mVideoUrl = RecipeAdapter.videoUrl;

                if(savedInstanceState != null){
                    playWhenReady = savedInstanceState.getBoolean("playWhenReady");
                    playbackPosition = savedInstanceState.getLong("playbackPosition");
                }

                    stepDescription.setText((CharSequence) descriptionsArray.get(mCurrentStep));

                    if (checkInternetConnection()) {
                        noVideo.setVisibility(View.GONE);
                            if ((mVideoUrl.get(mCurrentStep)) != null){
                                videoPlayerView.setVisibility(View.VISIBLE);
                                noVideo.setVisibility(View.GONE);
                                // Set player
                                initializePlayer(rootView.getContext(), mVideoUrl.get(mCurrentStep).toString());
                            }else{
                                videoPlayerView.setVisibility(View.INVISIBLE);
                                noVideo.setVisibility(View.VISIBLE);
                            }
                    } else {
                        videoPlayerView.setVisibility(View.INVISIBLE);
                        noVideo.setVisibility(View.VISIBLE);
                    }
            return rootView;
        }

        private void initializePlayer(Context context, String videoUrl) {

            player = ExoPlayerFactory.newSimpleInstance(
                    new DefaultRenderersFactory(context),
                    new DefaultTrackSelector(), new DefaultLoadControl());

            videoPlayerView.setPlayer(player);

            Uri uri = Uri.parse(videoUrl);
            MediaSource mediaSource = buildMediaSource(uri);
            player.prepare(mediaSource, true, false);

            player.seekTo(currentWindow, playbackPosition);

            player.setPlayWhenReady(playWhenReady);

        }

        private MediaSource buildMediaSource(Uri uri) {
            return new ExtractorMediaSource.Factory(
                    new DefaultHttpDataSourceFactory("exoplayer-codelab")).
                    createMediaSource(uri);
        }

        @Override
        public void onResume() {
            super.onResume();
            if((player != null)&&(playWhenReady)){
                player.setPlayWhenReady(true);
            }
        }

//        @Override
//        public void onPause() {
//            super.onPause();
//            if(player != null){
//                if(player.getPlayWhenReady()){
//                    player.setPlayWhenReady(false);
//                    playWhenReady = true;
//                }else{
//                    playWhenReady = false;
//                }
//            }
//        }

        @Override
        public void onDestroyView() {
            super.onDestroyView();
            releasePlayer();
        }

        private void releasePlayer() {
            if (player != null) {
                playbackPosition = player.getCurrentPosition();
                currentWindow = player.getCurrentWindowIndex();
                playWhenReady = player.getPlayWhenReady();
                player.release();
                player = null;
            }
        }

        private boolean checkInternetConnection() {
            ConnectivityManager connectivityManager = (ConnectivityManager) rootView.getContext().getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo activeNetworkInfo = null;
            if (connectivityManager != null) {
                activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
            }
            return activeNetworkInfo != null && activeNetworkInfo.isConnected();
        }

        @Override
        public void onSaveInstanceState(@NonNull Bundle outState) {
            super.onSaveInstanceState(outState);

            outState.putBoolean("playWhenReady",playWhenReady);
            outState.putLong("playbackPosition",player.getCurrentPosition());
        }

    @Override
    public void onPause() {
        super.onPause();
        if (player != null) {
            pauseExoPlayerState();
        }
    }

//    VIDEO IS RELEASED ON PAUSE
    private void pauseExoPlayerState() {
        playWhenReady = player.getPlayWhenReady();
        playbackPosition = player.getCurrentPosition();
        player.release();
    }

    public void setStepDetail(int currentStep) {
       this.mCurrentStep = currentStep;
    }
    }
