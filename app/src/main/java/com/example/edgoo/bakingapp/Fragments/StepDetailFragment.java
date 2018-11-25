package com.example.edgoo.bakingapp.Fragments;


import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.edgoo.bakingapp.R;
import com.example.edgoo.bakingapp.RecipeAdapter;
import com.google.android.exoplayer2.DefaultLoadControl;
import com.google.android.exoplayer2.DefaultRenderersFactory;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.LoadControl;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.extractor.DefaultExtractorsFactory;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.trackselection.TrackSelector;
import com.google.android.exoplayer2.ui.SimpleExoPlayerView;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.upstream.DefaultHttpDataSourceFactory;
import com.google.android.exoplayer2.util.Util;

import java.util.ArrayList;
import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;


public class StepDetailFragment extends Fragment {

    private int mCurrentStep;
    @BindView(R.id.step_description)
    TextView description;
    @BindView(R.id.no_video)
    RelativeLayout noVideoTxt;
    @BindView(R.id.playerView)
    SimpleExoPlayerView mPlayerView;
    private SimpleExoPlayer mExoPlayer;
    final ArrayList mVideoUrl = RecipeAdapter.videoUrl;
    boolean playWhenReady = false;
    int currentWindow = 0;
    long playbackPosition = 0;

    public StepDetailFragment() {}

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.recipe_step, container, false);
        ButterKnife.bind(this, rootView);

        final ArrayList descriptionsArray = RecipeAdapter.description;
        final String recipeName = RecipeAdapter.recipeName;

        if (savedInstanceState == null) {
            ConnectivityManager connMgr = (ConnectivityManager) Objects.requireNonNull(getActivity())
                    .getSystemService(Context.CONNECTIVITY_SERVICE);
            assert connMgr != null;
            NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
            if (!mVideoUrl.get(mCurrentStep).equals("") && (networkInfo != null && networkInfo.isConnected())) {
                noVideoTxt.setVisibility(View.GONE);
                initializePlayer(Uri.parse(String.valueOf(mVideoUrl.get(mCurrentStep))));
            } else {
                mPlayerView.setVisibility(View.INVISIBLE);
                noVideoTxt.setVisibility(View.VISIBLE);
            }
        }

        if (savedInstanceState != null) {
            mCurrentStep = (int) savedInstanceState.getSerializable("ser");
            if (!mVideoUrl.get(mCurrentStep).equals("")) {
                noVideoTxt.setVisibility(View.GONE);
                initializePlayer(Uri.parse(String.valueOf(mVideoUrl.get(mCurrentStep))));
            } else {
                mPlayerView.setVisibility(View.INVISIBLE);
                noVideoTxt.setVisibility(View.VISIBLE);
            }
        }

//        BECAUSE IT IS MISSING STEP 7
        if (recipeName.contains("Y") && mCurrentStep >= 8) {
            --mCurrentStep;
        }

//        GET AND SET DESCRIPTION
        description.setText((CharSequence) descriptionsArray.get(mCurrentStep));

        return rootView;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    private void initializePlayer(Uri mediaUri) {
        if (mExoPlayer == null) {
            TrackSelector trackSelector = new DefaultTrackSelector();
            LoadControl loadControl = new DefaultLoadControl();
            mExoPlayer = ExoPlayerFactory.newSimpleInstance(getActivity(), trackSelector, loadControl);
            mPlayerView.setPlayer(mExoPlayer);

            String userAgent = Util.getUserAgent(getActivity(), "Baking video");
            MediaSource mediaSource = new ExtractorMediaSource(mediaUri, new DefaultDataSourceFactory(
                    Objects.requireNonNull(getActivity()), userAgent), new DefaultExtractorsFactory(), null, null);
            mExoPlayer.prepare(mediaSource);
            mExoPlayer.setPlayWhenReady(true);
        }
    }

    private void releasePlayer() {
        if (mExoPlayer != null) {
            playbackPosition = mExoPlayer.getCurrentPosition();
            currentWindow = mExoPlayer.getCurrentWindowIndex();
            playWhenReady = mExoPlayer.getPlayWhenReady();
            mExoPlayer.release();
            mExoPlayer = null;
        }
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putSerializable("ser", mCurrentStep);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (mVideoUrl != null)
            releasePlayer();
    }

    @Override
    public void onResume() {
        super.onResume();
        if((mExoPlayer != null)&&(playWhenReady)){
            mExoPlayer.setPlayWhenReady(true);
        }
    }

    public void setStepsModel(int currentStep) {
        this.mCurrentStep = currentStep;
    }

    @Override
    public void onPause() {
        super.onPause();
        if(mExoPlayer != null){
            if(mExoPlayer.getPlayWhenReady()){
                mExoPlayer.setPlayWhenReady(false);
                playWhenReady = true;
            }else{
                playWhenReady = false;
            }
        }
    }
}
