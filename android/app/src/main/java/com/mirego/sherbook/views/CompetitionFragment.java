package com.mirego.sherbook.views;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mirego.sherbook.R;
import com.mirego.sherbook.SherbookApplication;
import com.mirego.sherbook.adapters.FacultyAdapter;
import com.mirego.sherbook.adapters.PostAdapter;
import com.mirego.sherbook.controllers.FeedController;
import com.mirego.sherbook.models.Faculty;
import com.mirego.sherbook.viewdatas.FacultyViewData;
import com.mirego.sherbook.viewdatas.PostViewData;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CompetitionFragment extends Fragment {

    @BindView(R.id.competition_root)
    ViewGroup root;

    @BindView(R.id.tv_no_faculties)
    TextView tvNoPosts;

    @BindView(R.id.rv_faculties)
    RecyclerView rvPosts;

    @Inject
    FeedController feedController;

    private FacultyAdapter facultyAdapatater;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        View competitionView = inflater.inflate(R.layout.fragment_competition, container, false);

        ((SherbookApplication) getActivity().getApplication()).component().inject(this);

        ButterKnife.bind(this, competitionView);

        configurePostsRecyclerView();

        return competitionView;
    }

    private void configurePostsRecyclerView() {
        facultyAdapatater = new FacultyAdapter(getContext());
        rvPosts.setLayoutManager(new LinearLayoutManager(getContext()));
        DividerItemDecoration itemDecoration = new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL);
        itemDecoration.setDrawable(getResources().getDrawable(R.drawable.list_spacing, null));
        rvPosts.addItemDecoration(itemDecoration);
        rvPosts.setAdapter(facultyAdapatater);
    }

    @Override
    public void onResume() {
        super.onResume();
        fetchFaculties();
    }

    private void fetchFaculties() {
        feedController.getFaculties(new FeedController.RequestCallback<List<FacultyViewData>>() {
            @Override
            public void onSuccess(List<FacultyViewData> result) {
                boolean hasPosts = result != null && result.size() != 0;

                rvPosts.setVisibility(hasPosts ? View.VISIBLE : View.GONE);
                tvNoPosts.setVisibility(!hasPosts ? View.VISIBLE : View.GONE);

                if (hasPosts) {
                    facultyAdapatater.populatePosts(result);
                }

            }

            @Override
            public void onError() {
                final Snackbar snackbar = Snackbar.make(root, R.string.error_cannot_load_posts, Snackbar.LENGTH_INDEFINITE);

                rvPosts.setVisibility(View.GONE);
                tvNoPosts.setVisibility(View.VISIBLE);

                snackbar.setAction(R.string.retry, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        fetchFaculties();
                    }
                });
                snackbar.show();
            }
        });
    }

}
