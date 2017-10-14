package com.mirego.sherbook.adapters;

import android.content.Context;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import com.bumptech.glide.Glide;
import com.mirego.sherbook.R;
import com.mirego.sherbook.models.Post;
import com.mirego.sherbook.viewdatas.PostViewData;
import com.mirego.sherbook.views.HomeFragment;

import org.w3c.dom.Text;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.PostViewHolder> {

    private List<PostViewData> posts;
    private final Context context;

    public PostAdapter(Context context) {
        this.context = context;
    }

    @Override
    public PostViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_post, parent, false);
        final PostViewHolder viewHolder = new PostViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(PostViewHolder holder, int position) {

        PostViewData postViewData = posts.get(position);
        if (postViewData != null) {
            holder.tvAuthor.setText(postViewData.author());
            holder.tvAuthor.setVisibility(postViewData.author() != null ? View.VISIBLE : View.GONE);

            holder.tvMessage.setText(postViewData.message());
            holder.tvMessage.setVisibility(postViewData.message() != null ? View.VISIBLE : View.GONE);

            Glide.with(context).load(postViewData.imageUrl()).into(holder.ivPhoto);
            holder.ivPhoto.setVisibility(postViewData.imageUrl() != null ? View.VISIBLE : View.GONE);

            String uri = postViewData.videoUrl();

            if(uri != null) {
                holder.vvVideo.setVideoURI(Uri.parse(uri));
                holder.vvVideo.start();
                holder.vvVideo.setVisibility(View.VISIBLE);
            } else {
                holder.vvVideo.setVisibility(View.GONE);
            }

            holder.id.setText(postViewData.id());

            //holder.rbRating.setRating(postViewData.rating());

        }
    }

    public void populatePosts(List<PostViewData> posts) {
        this.posts = posts;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        if (posts == null) {
            return 0;
        }
        return posts.size();
    }

    public static class PostViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tv_author)
        TextView tvAuthor;

        @BindView(R.id.tv_message)
        TextView tvMessage;

        @BindView(R.id.iv_photo)
        ImageView ivPhoto;

        @BindView(R.id.vv_video)
        VideoView vvVideo;

        @BindView(R.id.rb_rating)
        RatingBar rbRating;

        @BindView(R.id.tv_id)
        TextView id;



        public PostViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            addListenerOnRatingBar();
        }

        public void addListenerOnRatingBar() {

            //if rating value is changed,
            //display the current rating value in the result (textview) automatically
            rbRating.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
                public void onRatingChanged(RatingBar ratingBar, float rating,
                                            boolean fromUser) {
                    rbRating.setRating(rating);
                }
            });
        }

    }

}
