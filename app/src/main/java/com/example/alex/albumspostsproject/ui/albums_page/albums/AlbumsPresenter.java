package com.example.alex.albumspostsproject.ui.albums_page.albums;

import android.support.annotation.NonNull;
import android.util.Log;

import com.example.alex.albumspostsproject.data.entity.AlbumModel;
import com.example.alex.albumspostsproject.data.entity.PhotoModel;
import com.example.alex.albumspostsproject.data.network.RetrofitService;

import java.util.ArrayList;
import java.util.Random;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AlbumsPresenter implements AlbumsContract.Presenter {
    private AlbumsContract.View mView;
    private RetrofitService mService;

    AlbumsPresenter(RetrofitService service) {
        mService = service;

    }

    @Override
    public void downloadAlbumsList() {
        mView.showLoadingIndicator();
        mService.getAlbums()
                .enqueue(new Callback<ArrayList<AlbumModel>>() {
                    @Override
                    public void onResponse(@NonNull Call<ArrayList<AlbumModel>> call, @NonNull Response<ArrayList<AlbumModel>> response) {
                        if (response.body() != null && response.isSuccessful()) {
                            if (isViewAttached()) {
                                mView.onSuccess(randomList(response.body()));
                                downloadRandomImage(response.body());
                            }
                        } else {
                            if (isViewAttached())
                                mView.onFailture(response.message());
                        }

                        mView.hideLoadingIndicator();
                    }

                    @Override
                    public void onFailure(@NonNull Call<ArrayList<AlbumModel>> call, @NonNull Throwable t) {
                        if (isViewAttached()) {
                            mView.onFailture(t.getLocalizedMessage());
                            mView.hideLoadingIndicator();
                        }

                    }
                });
    }

    private void downloadRandomImage(ArrayList<AlbumModel> arrayList) {
        final Random random = new Random();
        int albumId = arrayList.get(random.nextInt(arrayList.size())).getUserId();

        mService.getPhotos(albumId)
                .enqueue(new Callback<ArrayList<PhotoModel>>() {
                    @Override
                    public void onResponse(@NonNull Call<ArrayList<PhotoModel>> call, @NonNull Response<ArrayList<PhotoModel>> response) {
                        if (response.body() != null && response.isSuccessful()) {
                            if (isViewAttached())
                            mView.backgroundRandomImage(response.body().get(random.nextInt(response.body().size())).getUrl());

                        }
                    }

                    @Override
                    public void onFailure(@NonNull Call<ArrayList<PhotoModel>> call, @NonNull Throwable t) {

                    }
                });

    }

    private ArrayList<AlbumModel> randomList(ArrayList<AlbumModel> arrayList) {
        ArrayList<AlbumModel> randomAlbumList = new ArrayList<>();
        Random random = new Random();

        int Low = 10;
        int High = 15;
        int res = random.nextInt(High - Low) + Low;

        for (int i = 0; i < res; i++) {
            randomAlbumList.add(arrayList.get(random.nextInt(arrayList.size())));
            Log.d("AlbumsPresenter", "randomList: " + randomAlbumList.get(i).getTitle());
        }
        return randomAlbumList;
    }

    @Override
    public void bind(AlbumsContract.View view) {
        mView = view;
    }

    public void unbind() {
        mView = null;
    }


    private boolean isViewAttached() {
        return mView != null;
    }
}
