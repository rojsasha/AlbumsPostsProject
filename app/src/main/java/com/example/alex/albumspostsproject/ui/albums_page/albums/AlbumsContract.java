package com.example.alex.albumspostsproject.ui.albums_page.albums;

import com.example.alex.albumspostsproject.IProgressBar;
import com.example.alex.albumspostsproject.Lifecycle;
import com.example.alex.albumspostsproject.data.entity.AlbumModel;

import java.util.ArrayList;

public interface AlbumsContract {

    interface View extends IProgressBar {
        void onSuccess(ArrayList<AlbumModel> model);
        void backgroundRandomImage(String url);

        void onFailture(String msg);
    }

    interface Presenter extends Lifecycle<View> {
        void downloadAlbumsList();

    }
}
