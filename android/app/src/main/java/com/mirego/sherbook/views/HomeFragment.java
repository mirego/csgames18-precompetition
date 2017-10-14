package com.mirego.sherbook.views;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.mirego.sherbook.R;
import com.mirego.sherbook.SherbookApplication;
import com.mirego.sherbook.adapters.PostAdapter;
import com.mirego.sherbook.controllers.FeedController;
import com.mirego.sherbook.models.Post;
import com.mirego.sherbook.viewdatas.PostViewData;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class HomeFragment extends Fragment {

    private Post selectedPost;

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

    private List<PostViewData> lstPosts;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        View homeView = inflater.inflate(R.layout.fragment_home, container, false);

        ((SherbookApplication) getActivity().getApplication()).component().inject(this);
        ButterKnife.bind(this, homeView);

        configurePostsRecyclerView();

        fabNewPost.setOnClickListener(new View.OnClickListener() {
            public void onClick(final View v) {
                // Creating alert Dialog with one Button
                AlertDialog.Builder alertDialog = new AlertDialog.Builder(getContext());


                // Setting Dialog Title
                alertDialog.setTitle("Nouveau post");

                //setting layout
                LinearLayout layout = new LinearLayout(getContext());
                layout.setOrientation(LinearLayout.VERTICAL);

                //Setting Author
                final EditText authorInput = new EditText(getContext());
                authorInput.setHint("Auteur");
                layout.addView(authorInput);

                // Setting Message view
                final EditText messageInput = new EditText(getContext());
                messageInput.setHint("Message");
                messageInput.setGravity(Gravity.TOP);
                messageInput.setLines(4);
                layout.addView(messageInput);

                alertDialog.setView(layout);


                // Setting Positive "ajouter" Button
                alertDialog.setPositiveButton("Ajouter",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,int which) {
                                // Write your code here to execute after dialog
                                Post post  = new Post();
                                post.setAuthor(authorInput.getText().toString());
                                post.setMessage(messageInput.getText().toString());
                                post.setId(UUID.randomUUID().toString());
                                post.setDate(new Date());

                                PostViewData postViewData = new PostViewData(post, getContext());
                                lstPosts.add(postViewData);

                                postAdapter.populatePosts(lstPosts);

                            }
                        });
                // Setting Negative "annuler" Button
                alertDialog.setNegativeButton("Annuler",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                // Write your code here to execute after dialog
                                dialog.cancel();
                            }
                        });

                // closed

                // Showing Alert Message
                alertDialog.show();

            }
        });

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

                //save the post in a variable to add in the futur
                lstPosts = result;

                boolean hasPosts = result != null && result.size() != 0;

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

    @OnClick(R.id.fab_new_post)
    public void onNewPostClicked(View view) {
        Snackbar.make(view, R.string.not_implemented_yet, Snackbar.LENGTH_SHORT).show();
    }
}
