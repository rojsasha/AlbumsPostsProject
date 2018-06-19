package com.example.alex.albumspostsproject.ui.posts_page.comments;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.alex.albumspostsproject.R;
import com.example.alex.albumspostsproject.data.entity.CommentsModel;

import java.util.ArrayList;

public class CommentsAdapter extends ArrayAdapter {
    private ArrayList<CommentsModel> arrayList;

    CommentsAdapter(@NonNull Context context, ArrayList<CommentsModel> arrayList) {
        super(context, 0, arrayList);
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ViewHolder holder;

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.items_comments, parent, false);
            holder = new ViewHolder(convertView);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        CommentsModel model = (CommentsModel) getItem(position);
        if (model != null) {
            holder.tvUserId.setText(String.valueOf(model.getPostId()));
            holder.tvId.setText(String.valueOf(model.getId()));
            holder.tvName.setText(model.getName());
            holder.tvBody.setText(model.getBody());
            holder.tvEmail.setText(model.getEmail());
        }
        return convertView;
    }

    class ViewHolder {
        TextView tvUserId, tvId, tvName, tvBody, tvEmail;

        ViewHolder(View view) {
            tvUserId = view.findViewById(R.id.tvUserId);
            tvId = view.findViewById(R.id.tvId);
            tvName = view.findViewById(R.id.tvName);
            tvBody = view.findViewById(R.id.tvBody);
            tvEmail = view.findViewById(R.id.tvEmail);
        }
    }
}
