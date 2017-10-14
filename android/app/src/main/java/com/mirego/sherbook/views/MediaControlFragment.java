package com.mirego.sherbook.views;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import com.mirego.sherbook.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MediaControlFragment extends Fragment {
    public MediaControlFragment() {
        // Required empty public constructor
    }

    @BindView(R.id.media_control_seekbar)
    SeekBar seekBar;

    @BindView(R.id.media_control_play)
    ImageButton PlayButton;

    @BindView(R.id.media_control_name)
    TextView Name;

    @BindView(R.id.media_control_time)
    TextView Time;

    @BindView(R.id.media_control_image)
    ImageView Image;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_media_control, container, false);
        ButterKnife.bind(this, rootView);
        return rootView;
    }

    @OnClick(R.id.media_control_play)
    public void onPlayButtonPressed(View view) {
        Snackbar.make(view, R.string.not_implemented_yet, Snackbar.LENGTH_SHORT).show();
    }
}
