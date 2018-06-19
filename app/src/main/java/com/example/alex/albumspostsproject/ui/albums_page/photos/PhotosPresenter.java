package com.example.alex.albumspostsproject.ui.albums_page.photos;

import android.support.annotation.NonNull;

import com.example.alex.albumspostsproject.data.entity.PhotoModel;
import com.example.alex.albumspostsproject.data.network.RetrofitService;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PhotosPresenter implements PhotosContract.Presenter {
    private PhotosContract.View mView;
    private RetrofitService mService;

    PhotosPresenter(RetrofitService service) {
        mService = service;
    }

    @Override
    public void loadImageFromAlbum(int id) {
        mView.showLoadingIndicator();
        mService.getPhotos(id)
                .enqueue(new Callback<ArrayList<PhotoModel>>() {
                    @Override
                    public void onResponse(@NonNull Call<ArrayList<PhotoModel>> call, @NonNull Response<ArrayList<PhotoModel>> response) {
                        if (response.body() != null && response.isSuccessful()) {
                            if (isViewAttached())
                                mView.onSuccess(response.body());
                        } else {
                            if (isViewAttached())
                                mView.onError(response.message());
                        }
                        mView.hideLoadingIndicator();
                    }

                    @Override
                    public void onFailure(@NonNull Call<ArrayList<PhotoModel>> call, @NonNull Throwable t) {
                        if (isViewAttached()) {
                            mView.onError(t.getLocalizedMessage());
                            mView.hideLoadingIndicator();
                        }

                    }
                });
    }


    @Override
    public void bind(PhotosContract.View view) {
        mView = view;
    }

    @Override
    public void unbind() {
        mView = null;
    }

    private boolean isViewAttached() {
        return mView != null;
    }
}
