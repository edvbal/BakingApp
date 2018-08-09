package com.example.edvinas.bakingapp.ui.instructions;

import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;

import com.example.edvinas.bakingapp.R;
import com.example.edvinas.bakingapp.base.BaseFragment;
import com.example.edvinas.bakingapp.utils.network.pojo.Steps;
import com.google.android.exoplayer2.DefaultLoadControl;
import com.google.android.exoplayer2.DefaultRenderersFactory;
import com.google.android.exoplayer2.ExoPlayer;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.Player;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.ui.SimpleExoPlayerView;
import com.google.android.exoplayer2.upstream.DefaultHttpDataSourceFactory;

import butterknife.BindView;

import static com.google.android.exoplayer2.Player.STATE_READY;

public class InstructionsFragment extends BaseFragment {
    private static final String KEY_STEPS = "key.steps";
    private static final String KEY_PLAYBACK_POSITION = "key.currentPlayerPosition";
    private static final String KEY_PLAYBACK_STATE = "key.currentPlayerState";
    @BindView(R.id.descriptionTextView)
    TextView descriptionTextView;
    @BindView(R.id.emptyVideoTextView)
    TextView emptyVideoTextView;
    @BindView(R.id.simpleExoPlayerView)
    SimpleExoPlayerView simpleExoPlayerView;
    private SimpleExoPlayer exoPlayer;
    private Uri videoUri = null;
    long playbackPosition = 0;
    boolean playbackState = true;
    private Steps steps;

    public static InstructionsFragment newInstance(Steps steps) {
        InstructionsFragment fragment = new InstructionsFragment();
        Bundle args = new Bundle();
        args.putSerializable(KEY_STEPS, steps);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() == null) {
            return;
        }
        steps = (Steps) getArguments().getSerializable(KEY_STEPS);
        if (steps == null) {
            return;
        }
        if (savedInstanceState != null) {
            playbackPosition = savedInstanceState.getLong(KEY_PLAYBACK_POSITION);
            playbackState = savedInstanceState.getBoolean(KEY_PLAYBACK_STATE);
        }
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        descriptionTextView.setText(steps.getDescription());
        simpleExoPlayerView.setVisibility(View.VISIBLE);
        if (doesVideoUrlExist(steps.getVideoUrl()) && exoPlayer == null) {
            videoUri = Uri.parse(steps.getVideoUrl());
            initPlayer(videoUri);
        } else {
            emptyVideoTextView.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        if (videoUri != null && exoPlayer == null) {
            initPlayer(videoUri);
        }
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_instructions;
    }

    private boolean doesVideoUrlExist(String videoUrl) {
        return videoUrl != null && !videoUrl.isEmpty();
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putLong(KEY_PLAYBACK_POSITION, playbackPosition);
        outState.putBoolean(KEY_PLAYBACK_STATE, playbackState);
    }

    private void initPlayer(Uri videoUri) {
        exoPlayer = ExoPlayerFactory.newSimpleInstance(
                new DefaultRenderersFactory(getContext()),
                new DefaultTrackSelector(),
                new DefaultLoadControl()
        );
        simpleExoPlayerView.setPlayer(exoPlayer);
        MediaSource mediaSource = buildMediaSource(videoUri);
        exoPlayer.prepare(mediaSource);
        if (playbackPosition != 0) {
            exoPlayer.seekTo(playbackPosition);
        }
        exoPlayer.setPlayWhenReady(playbackState);
    }

    private MediaSource buildMediaSource(Uri uri) {
        return new ExtractorMediaSource.Factory(
                new DefaultHttpDataSourceFactory("example2example")).
                createMediaSource(uri);
    }

    @Override
    public void onStop() {
        super.onStop();
        releasePlayer();
    }

    @Override
    public void onPause() {
        super.onPause();
        releasePlayer();
    }

    private void releasePlayer() {
        if (exoPlayer != null) {
            playbackState = exoPlayer.getPlayWhenReady();
            playbackPosition = exoPlayer.getCurrentPosition();
            exoPlayer.stop();
            exoPlayer.release();
            exoPlayer = null;
        }
    }
}
