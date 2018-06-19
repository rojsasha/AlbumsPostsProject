package com.example.alex.albumspostsproject.ui.posts_page.posts;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.alex.albumspostsproject.R;
import com.example.alex.albumspostsproject.data.entity.PostsModel;

import java.util.ArrayList;

public class PostsAdapter extends ArrayAdapter {;
    private Context mContext;

    PostsAdapter(@NonNull Context context, ArrayList<PostsModel> arrayList) {
        super(context, 0, arrayList);
        mContext = context;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null){
            convertView = LayoutInflater.from(mContext).inflate(R.layout.items_listview_albums
                    , parent ,false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        }else {
            holder = (ViewHolder) convertView.getTag();
        }
        PostsModel model = (PostsModel) getItem(position);
        if (model != null){

            holder.tvAlbumsTitle.setText(model.getTitle());

            holder.tvTitle.setText(String.valueOf(model.getUserId()));
        }
        return convertView;
    }

    class ViewHolder{
        TextView tvTitle,tvAlbumsTitle;

        ViewHolder(View view){
            tvTitle = view.findViewById(R.id.tvTitle);
            tvAlbumsTitle = view.findViewById(R.id.tvAlbumsTitle);
        }
    }
}
