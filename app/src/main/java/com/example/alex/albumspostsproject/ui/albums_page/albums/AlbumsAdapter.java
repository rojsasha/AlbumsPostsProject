package com.example.alex.albumspostsproject.ui.albums_page.albums;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.alex.albumspostsproject.R;
import com.example.alex.albumspostsproject.data.entity.AlbumModel;

import java.util.ArrayList;

public class AlbumsAdapter extends ArrayAdapter {
    private Context mContext;
    AlbumsAdapter(@NonNull Context context, ArrayList<AlbumModel> arrayList) {
        super(context, 0,arrayList);
        mContext = context;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ViewHolder holder;

        if (convertView == null){
            convertView = LayoutInflater.from(mContext).inflate(R.layout.items_listview_albums, parent,false);
            holder = new ViewHolder(convertView);

            convertView.setTag(holder);
        }else {
            holder = (ViewHolder) convertView.getTag();
        }
        AlbumModel model = (AlbumModel) getItem(position);
        if (model!= null)
        holder.tvTitleAlbum.setText(model.getTitle());
        holder.tvTitle.setText(String.valueOf(model.getUserId()));
        return convertView;
    }

    class ViewHolder{
        TextView tvTitleAlbum, tvTitle;
        ViewHolder(View view){
            tvTitle = view.findViewById(R.id.tvTitle);

            tvTitleAlbum = view.findViewById(R.id.tvAlbumsTitle);
        }

    }
}
