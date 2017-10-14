package com.mirego.sherbook.views;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
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
import com.mirego.sherbook.adapters.PostAdapter;
import com.mirego.sherbook.controllers.FeedController;
import com.mirego.sherbook.models.Post;
import com.mirego.sherbook.viewdatas.PostViewData;

import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class HomeFragment extends Fragment {

    @BindView(R.id.home_root)
    ViewGroup root;

    @BindView(R.id.tv_no_posts)
    TextView tvNoPosts;

    @BindView(R.id.rv_posts)
    RecyclerView rvPosts;

    @BindView(R.id.fab_new_post)
    FloatingActionButton fabNewPost;

    @Inject
    FeedController feedController;

    private PostAdapter postAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        View homeView = inflater.inflate(R.layout.fragment_home, container, false);

        ((SherbookApplication) getActivity().getApplication()).component().inject(this);
        ButterKnife.bind(this, homeView);

        configurePostsRecyclerView();

        return homeView;
    }

    private void configurePostsRecyclerView() {
        postAdapter = new PostAdapter(getContext());
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
        feedController.getPosts(new FeedController.RequestCallback<List<PostViewData>>() {
            @Override
            public void onSuccess(List<PostViewData> result) {
                boolean hasPosts = result != null && result.size() != 0;

                if (hasPosts) result.add(getCustomPostViewData());

                rvPosts.setVisibility(hasPosts ? View.VISIBLE : View.GONE);
                tvNoPosts.setVisibility(!hasPosts ? View.VISIBLE : View.GONE);

                if (hasPosts) {
                    postAdapter.populatePosts(result);
                }

                fabNewPost.setEnabled(true);
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

        fabNewPost.setEnabled(false);
    }

    public PostViewData getCustomPostViewData() {

        Post post = new Post();

        post.setAuthor("Some person who podcasts");
        post.setDate(new Date());
        post.setMessage("Hey, I podcast stuff, please listen to my podcast for fun. Please like, share and maybe follow? :)");
        post.setId("489557-283729");


        Post.Attachment attachment = post.new Attachment();

        attachment.setType(Post.SOUND_FILE_TYPE);
        //attachment.setUrl("SOMTHING");
        post.setAttachment(attachment);
        PostViewData postViewData = new PostViewData(post, getActivity());

        return postViewData;
    }

    @OnClick(R.id.fab_new_post)
    public void onNewPostClicked(View view) {
        Snackbar.make(view, R.string.not_implemented_yet, Snackbar.LENGTH_SHORT).show();
    }
}
