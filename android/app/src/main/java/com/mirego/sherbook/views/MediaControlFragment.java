package com.mirego.sherbook.views;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mirego.sherbook.R;

import butterknife.ButterKnife;

public class MediaControlFragment extends Fragment {
    public MediaControlFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_media_control, container, false);
        ButterKnife.bind(this, rootView);
        return rootView;
    }
}
