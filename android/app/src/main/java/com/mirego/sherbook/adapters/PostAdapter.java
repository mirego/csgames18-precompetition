package com.mirego.sherbook.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.bitmap_recycle.IntegerArrayAdapter;
import com.mirego.sherbook.R;
import com.mirego.sherbook.viewdatas.PostViewData;

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

            holder.tvFaculty.setText(postViewData.faculty());
            holder.tvFaculty.setVisibility(postViewData.faculty() != null ? View.VISIBLE : View.GONE);

            holder.tvMessage.setText(postViewData.message());
            holder.tvMessage.setVisibility(postViewData.message() != null ? View.VISIBLE : View.GONE);

            holder.tvLikes.setText(String.valueOf(postViewData.likes()));
            holder.tvLikes.setVisibility(postViewData.likes() != -1 ? View.VISIBLE : View.GONE);

            Glide.with(context).load(postViewData.imageUrl()).into(holder.ivPhoto);
            holder.ivPhoto.setVisibility(postViewData.imageUrl() != null ? View.VISIBLE : View.GONE);
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

        @BindView(R.id.tv_faculty)
        TextView tvFaculty;

        @BindView(R.id.tv_message)
        TextView tvMessage;

        @BindView(R.id.tv_likes)
        TextView tvLikes;

        @BindView(R.id.iv_photo)
        ImageView ivPhoto;

        @BindView(R.id.button_id)
        Button button;

        public PostViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        @OnClick(R.id.button_id)
        public void onButtonClick(View view) {
            tvLikes.setText(String.valueOf(Integer.parseInt(tvLikes.getText().toString())+1));
            tvLikes.setVisibility(View.VISIBLE);
        }
    }

}
