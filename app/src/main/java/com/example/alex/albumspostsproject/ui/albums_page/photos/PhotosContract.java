package com.example.alex.albumspostsproject.ui.albums_page.photos;

import com.example.alex.albumspostsproject.IProgressBar;
import com.example.alex.albumspostsproject.Lifecycle;
import com.example.alex.albumspostsproject.data.entity.PhotoModel;

import java.util.ArrayList;

public interface PhotosContract  {
    interface View extends IProgressBar {
        void onSuccess(ArrayList<PhotoModel> photoModels);

        void onError(String msg);

    }

    interface Presenter extends Lifecycle<View> {
        void loadImageFromAlbum(int id);

    }
}
