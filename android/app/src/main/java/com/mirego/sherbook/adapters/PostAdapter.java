package com.mirego.sherbook.adapters;

import android.content.Context;
import android.support.v4.app.FragmentManager;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.mirego.sherbook.R;
import com.mirego.sherbook.services.PlaybackService;
import com.mirego.sherbook.viewdatas.PostViewData;
import com.mirego.sherbook.views.MainActivity;
import com.mirego.sherbook.views.PodcastPostFragment;
import com.mirego.sherbook.views.PodcastView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

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


            PlaybackService playbackService = ((MainActivity)context).playbackService;

            if (playbackService != null)
            {
                holder.playbackService = playbackService;
                holder.podcastHolder.setVisibility(postViewData.IsAudio() ? View.VISIBLE : View.GONE);
                holder.SetDuration(playbackService.getDuration());
                holder.SetTitle(playbackService.getTrackName());
            }

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

        PlaybackService playbackService;

        @BindView(R.id.tv_author)
        TextView tvAuthor;

        @BindView(R.id.tv_message)
        TextView tvMessage;

        @BindView(R.id.iv_photo)
        ImageView ivPhoto;

        @BindView(R.id.image_podcast_play)
        ImageView playButton;

        @BindView(R.id.podcast_view)
        FrameLayout podcastHolder;

        @BindView(R.id.text_podcast_view)
        TextView tvPodcastViewName;

        @BindView(R.id.text_podcast_duration)
        TextView tvPodcastViewDuration;

        public PostViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

            playButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // This is the place we will be able to start the audio player UI
                    Toast.makeText(v.getContext(),
                            "I will start audio playing here",
                            Toast.LENGTH_LONG).show();
                }
            });
        }

        public void SetDuration(int duration){
            duration = duration / 1000;
            tvPodcastViewDuration.setText(String.format("%02d:%02d", duration / 60, duration % 60));
        }

        public void SetTitle(String title){
            tvPodcastViewName.setText(title);
        }

    }

}
