package com.mirego.sherbook.adapters;

import android.content.Context;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.mirego.sherbook.R;
import com.mirego.sherbook.viewdatas.FacultyViewData;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class FacultyAdapter extends RecyclerView.Adapter<FacultyAdapter.FacultyViewHolder> {

    private List<FacultyViewData> faculties;
    private final Context context;

    public FacultyAdapter(Context context) {
        this.context = context;
    }

    @Override
    public FacultyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_faculty, parent, false);
        final FacultyViewHolder viewHolder = new FacultyViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(FacultyViewHolder holder, int position) {

        FacultyViewData facultyViewData = faculties.get(position);
        if (facultyViewData != null) {
            holder.tvAuthor.setText(facultyViewData.nom());
            holder.tvAuthor.setVisibility(facultyViewData.nom() != null ? View.VISIBLE : View.GONE);

            holder.tvMessage.setText((String.valueOf(facultyViewData.score())));
            holder.tvMessage.setVisibility(facultyViewData.score() != -1 ? View.VISIBLE : View.GONE);

            Glide.with(context).load(facultyViewData.imageUrl()).into(holder.ivPhoto);
            holder.ivPhoto.setVisibility(facultyViewData.imageUrl() != null ? View.VISIBLE : View.GONE);
        }
    }

    public void populatePosts(List<FacultyViewData> faculties) {
        this.faculties = faculties;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        if (faculties == null) {
            return 0;
        }
        return faculties.size();
    }

    public static class FacultyViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tv_faculty_name)
        TextView tvAuthor;

        @BindView(R.id.tv_score)
        TextView tvMessage;

        @BindView(R.id.iv_photo_faculty)
        ImageView ivPhoto;


        public FacultyViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

    }

}
