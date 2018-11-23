package com.nanodegree.sam.baker.project.fragments;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.exoplayer2.DefaultLoadControl;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.LoadControl;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.extractor.DefaultExtractorsFactory;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.trackselection.TrackSelector;
import com.google.android.exoplayer2.ui.AspectRatioFrameLayout;
import com.google.android.exoplayer2.ui.SimpleExoPlayerView;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.util.Util;
import com.nanodegree.sam.baker.R;

import java.net.URI;

public class VideoPlayerFragment extends Fragment {
    private SimpleExoPlayer mExoPlayer;
    private SimpleExoPlayerView mPlayerView;
    private static final String DETAILS = "Details";
    private static final String USER_AGENT = "Cooking Instructions";
    private static final String SCREEN_FLAG = "ScreenFlag";
    private Uri VideoUri;
    private int resizeMode;

    public VideoPlayerFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_video_player, container, false);
        mPlayerView = (SimpleExoPlayerView) rootView.findViewById(R.id.cookPlayer);

        Bundle bundle = this.getArguments();

        String urlString = bundle.getString(DETAILS);

        this.VideoUri = Uri.parse(urlString);
        bundle.putString("UrlString",urlString);

        resizeMode = AspectRatioFrameLayout.RESIZE_MODE_FIT;
        initializePlayer(resizeMode,VideoUri);


        FloatingActionButton fb_back_navigator = rootView.findViewById(R.id.fabButtonBack);
        if (bundle.getBoolean(SCREEN_FLAG)) {
            fb_back_navigator.setVisibility(View.INVISIBLE);

        }
        fb_back_navigator.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                FragmentManager fragmentManager = getFragmentManager();
                if (fragmentManager.getBackStackEntryCount() > 0) {
                    fragmentManager.popBackStack();
                }

            }
        });


        return rootView;
    }

    @Override
    public void onResume(){
        super.onResume();

        resizeMode = AspectRatioFrameLayout.RESIZE_MODE_FILL;
        initializePlayer(resizeMode,VideoUri);

    }

    @Override
    public void onPause(){
        super.onPause();

        if(Util.SDK_INT <= 23) {
            releasePlayer();
        }
    }

    @Override
    public void onStop(){
        super.onStop();

        if(Util.SDK_INT <= 23) {
            releasePlayer();
        }
    }

    private void initializePlayer(int resizeModeFactor, Uri VideoUri) {

        Context context = getActivity();
        if (mExoPlayer == null) {

            TrackSelector trackSelector = new DefaultTrackSelector();
            LoadControl loadControl = new DefaultLoadControl();
            mExoPlayer = ExoPlayerFactory.newSimpleInstance(context, trackSelector, loadControl);
            mPlayerView.setDefaultArtwork(BitmapFactory.decodeResource(getResources(),R.drawable.ic_ondemand_video_24dp));
            mPlayerView.setPlayer(mExoPlayer);
            mPlayerView.setResizeMode(resizeModeFactor);
            String userAgent = Util.getUserAgent(context, USER_AGENT);
            MediaSource mediaSource = new ExtractorMediaSource(VideoUri, new DefaultDataSourceFactory(
                    context, userAgent), new DefaultExtractorsFactory(), null, null);
            mExoPlayer.prepare(mediaSource);
            mExoPlayer.setPlayWhenReady(true);
        }
    }

    private void releasePlayer() {
        mExoPlayer.stop();
        mExoPlayer.release();
        mExoPlayer = null;
    }

}
