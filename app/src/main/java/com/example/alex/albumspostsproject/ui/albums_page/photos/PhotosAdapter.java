package com.example.alex.albumspostsproject.ui.albums_page.photos;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.alex.albumspostsproject.R;
import com.example.alex.albumspostsproject.data.entity.PhotoModel;


import java.util.ArrayList;

public class PhotosAdapter extends ArrayAdapter {
    private Context mContext;

    PhotosAdapter(@NonNull Context context, ArrayList<PhotoModel> arrayList) {
        super(context, 0, arrayList);
        mContext = context;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ViewHolder holder;

        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.items_gridview_photos, parent, false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        PhotoModel model = (PhotoModel) getItem(position);
        if (model != null)
            Glide.with(mContext)
            .load(model.getUrl())
            .into(holder.imgPosterPhoto);
        holder.tvIdImage.setText(String.valueOf(model.getAlbumId()));






        return convertView;
    }


    class ViewHolder {
        ImageView imgPosterPhoto;
        TextView tvIdImage;

        ViewHolder(View view) {
            imgPosterPhoto = view.findViewById(R.id.imgAlbumsPhoto);
            tvIdImage = view.findViewById(R.id.tvIdImage);

        }
    }
}
