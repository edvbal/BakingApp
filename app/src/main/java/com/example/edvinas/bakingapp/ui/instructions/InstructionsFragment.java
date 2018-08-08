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
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.ui.SimpleExoPlayerView;
import com.google.android.exoplayer2.upstream.DefaultHttpDataSourceFactory;

import butterknife.BindView;

public class InstructionsFragment extends BaseFragment {
    private static final String KEY_STEPS = "key.steps";
    private SimpleExoPlayer exoPlayer;
    @BindView(R.id.descriptionTextView)
    TextView descriptionTextView;
    @BindView(R.id.emptyVideoTextView)
    TextView emptyVideoTextView;
    @BindView(R.id.simpleExoPlayerView)
    SimpleExoPlayerView simpleExoPlayerView;

    public static InstructionsFragment newInstance(Steps steps) {
        InstructionsFragment fragment = new InstructionsFragment();
        Bundle args = new Bundle();
        args.putSerializable(KEY_STEPS, steps);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (getArguments() == null) {
            return;
        }
        Steps steps = (Steps) getArguments().getSerializable(KEY_STEPS);
        if (steps == null) {
            return;
        }
        descriptionTextView.setText(steps.getDescription());
        String videoUrl = steps.getVideoUrl();
        if (videoUrl != null && !videoUrl.isEmpty() && exoPlayer == null) {
            Uri videoUri = Uri.parse(steps.getVideoUrl());
            simpleExoPlayerView.setVisibility(View.VISIBLE);
            initPlayer(videoUri);
        } else {
            emptyVideoTextView.setVisibility(View.VISIBLE);
        }
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
        exoPlayer.setPlayWhenReady(true);
    }

    private MediaSource buildMediaSource(Uri uri) {
        return new ExtractorMediaSource.Factory(
                new DefaultHttpDataSourceFactory("example2example")).
                createMediaSource(uri);
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_instructions;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (exoPlayer != null) {
            exoPlayer.stop();
            exoPlayer.release();
            exoPlayer = null;
        }
    }
}
