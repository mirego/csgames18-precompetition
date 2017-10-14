
package com.mirego.sherbook.views;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mirego.sherbook.R;
import com.mirego.sherbook.SherbookApplication;
import com.mirego.sherbook.adapters.FriendAdapter;
import com.mirego.sherbook.adapters.PostAdapter;
import com.mirego.sherbook.controllers.FeedController;
import com.mirego.sherbook.viewdatas.FriendViewData;
import com.mirego.sherbook.viewdatas.PostViewData;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FriendsFragment extends Fragment {

    @BindView(R.id.friends_root)
    ViewGroup root;

    @BindView(R.id.rv_friends)
    RecyclerView rvPosts;

    @BindView(R.id.tv_no_posts)
    TextView tvNoPosts;

    @Inject
    FeedController feedController;

    private FriendAdapter postAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_friends, container, false);

        ((SherbookApplication) getActivity().getApplication()).component().inject(this);
        ButterKnife.bind(this, rootView);

        configurePostsRecyclerView();
        return rootView;
    }


    private void configurePostsRecyclerView() {
        postAdapter = new FriendAdapter(getContext());
        rvPosts.setLayoutManager(new LinearLayoutManager(getContext()));
        DividerItemDecoration itemDecoration = new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL);
        itemDecoration.setDrawable(getResources().getDrawable(R.drawable.list_spacing, null));
        rvPosts.addItemDecoration(itemDecoration);
        rvPosts.setAdapter(postAdapter);
    }

    @Override
    public void onResume() {
        super.onResume();
        fetchPosts();
    }
    private void fetchPosts() {
        feedController.getFriendPosts(new FeedController.RequestCallback<List<FriendViewData>>() {
            @Override
            public void onSuccess(List<FriendViewData> result) {
                boolean hasPosts = result != null && result.size() != 0;

                rvPosts.setVisibility(hasPosts ? View.VISIBLE : View.GONE);
                tvNoPosts.setVisibility(!hasPosts ? View.VISIBLE : View.GONE);

                if (hasPosts) {
                    postAdapter.populatePosts(result);
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
                        fetchPosts();
                    }
                });
                snackbar.show();
            }
        });
    }
}