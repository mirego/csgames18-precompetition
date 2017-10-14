package com.mirego.sherbook.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.mirego.sherbook.R;
import com.mirego.sherbook.viewdatas.FriendViewData;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FriendAdapter extends RecyclerView.Adapter<FriendAdapter.FriendsViewHolder> {

    private List<FriendViewData> posts;
    private final Context context;

    public FriendAdapter(Context context) {
        this.context = context;
    }

    @Override
    public FriendAdapter.FriendsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_friend, parent, false);
        final FriendsViewHolder viewHolder = new FriendsViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(FriendAdapter.FriendsViewHolder holder, int position) {

        FriendViewData friendViewData = posts.get(position);
        if (friendViewData != null) {
            holder.tvNameFriend.setText(friendViewData.name());
            holder.tvNameFriend.setVisibility(friendViewData.name() != null ? View.VISIBLE : View.GONE);

            holder.tvCityFriend.setText(friendViewData.city());
            holder.tvCityFriend.setVisibility(friendViewData.city() != null ? View.VISIBLE : View.GONE);

            holder.tvAge.setText(friendViewData.age());
            holder.tvAge.setVisibility(friendViewData.age() != null ? View.VISIBLE : View.GONE);

            Glide.with(context).load(friendViewData.pictureUrl()).into(holder.ivPhotoFriend);
            holder.ivPhotoFriend.setVisibility(friendViewData.pictureUrl() != null ? View.VISIBLE : View.GONE);
        }
    }

    public void populatePosts(List<FriendViewData> posts) {
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


    public static class FriendsViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.iv_photoFriend)
        ImageView ivPhotoFriend;

        @BindView(R.id.tv_name)
        TextView tvNameFriend;

        @BindView(R.id.tv_city)
        TextView tvCityFriend;

        @BindView(R.id.tv_age)
        TextView tvAge;


        public FriendsViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

    }
}
