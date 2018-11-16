package com.example.edgoo.bakingapp.Fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.edgoo.bakingapp.R;
import com.example.edgoo.bakingapp.RecipeAdapter;
import com.example.edgoo.bakingapp.RecipeStep;
import com.example.edgoo.bakingapp.RecipeStepList;
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

public class StepVideoDescripFrag extends Fragment implements ExoPlayer.EventListener {

    //    EXOPLAYER
    private SimpleExoPlayer mExoPlayer;
    private ArrayList mVideoUrl;
    private ArrayList mThumbUrl;
    private int mStep_id;
    private Context mContext;


    public void VideoFragPass(Context context, ArrayList videoUrl, ArrayList thumbUrl, int step_id){
        mVideoUrl = videoUrl;
        mThumbUrl = thumbUrl;
        mStep_id = step_id;
        this.mContext = context;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.recipe_video_descrip_frag, container, false);

        ArrayList descriptionsArray = RecipeAdapter.description;
//        GETS STEP FROM ARRAY WITH STEPID AND DISPLAYS
        TextView descrip = rootView.findViewById(R.id.step_description);
        descrip.setText((CharSequence) descriptionsArray.get(mStep_id));


        //        GETS VIDEO AND SENDS TO EXO PLAYER
        if (!String.valueOf(mVideoUrl.get(mStep_id)).equals("")) {
            initializePlayer(rootView, Uri.parse(String.valueOf(mVideoUrl.get(mStep_id))));
        } else if (!String.valueOf(mThumbUrl.get(mStep_id)).equals("")) {
            initializePlayer(rootView, Uri.parse(String.valueOf(mThumbUrl.get(mStep_id))));
        } else {
            RelativeLayout noVideo = rootView.findViewById(R.id.no_video);
            SimpleExoPlayerView mPlayerView = rootView.findViewById(R.id.playerView);
            noVideo.setVisibility(View.VISIBLE);
            initializePlayer(rootView, Uri.parse(""));
            mPlayerView.setVisibility(View.INVISIBLE);
        }

        // Return the rootView
        return rootView;
    }

    private void initializePlayer(View rootView, Uri mediaUri){
        SimpleExoPlayerView mPlayerView = rootView.findViewById(R.id.playerView);
        if (mExoPlayer == null) {
            // Create an instance of the ExoPlayer.
            TrackSelector trackSelector = new DefaultTrackSelector();
            LoadControl loadControl = new DefaultLoadControl();
            mExoPlayer = ExoPlayerFactory.newSimpleInstance( mContext, trackSelector, loadControl);
            mPlayerView.setPlayer(mExoPlayer);

            // Set the ExoPlayer.EventListener to this activity.
            mExoPlayer.addListener(this);

            // Prepare the MediaSource.
            String userAgent = Util.getUserAgent(mContext, "ClassicalMusicQuiz");
            MediaSource mediaSource = new ExtractorMediaSource(mediaUri, new DefaultDataSourceFactory(
                    mContext, userAgent), new DefaultExtractorsFactory(), null, null);
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
