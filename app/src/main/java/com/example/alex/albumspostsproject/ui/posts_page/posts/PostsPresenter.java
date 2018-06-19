package com.example.alex.albumspostsproject.ui.posts_page.posts;

import android.support.annotation.NonNull;
import android.util.Log;
import com.example.alex.albumspostsproject.data.entity.PostsModel;
import com.example.alex.albumspostsproject.data.network.RetrofitService;

import java.util.ArrayList;
import java.util.Random;


import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PostsPresenter implements PostsContract.Presenter {
    private RetrofitService mService;
    private PostsContract.View mView;

    PostsPresenter(RetrofitService service) {
        mService = service;
    }

    @Override
    public void getPosts() {
        mView.showLoadingIndicator();
        mService.getPosts()
                .enqueue(new Callback<ArrayList<PostsModel>>() {
                    @Override
                    public void onResponse(@NonNull Call<ArrayList<PostsModel>> call, @NonNull Response<ArrayList<PostsModel>> response) {
                        if (response.body() != null && response.isSuccessful()) {
                            if (isViewAttached())
                                mView.onSuccess(randomList(response.body()));
                        } else {
                            if (isViewAttached())
                                mView.onError(response.message());
                        }
                        mView.hideLoadingIndicator();
                    }

                    @Override
                    public void onFailure(@NonNull Call<ArrayList<PostsModel>> call, @NonNull Throwable t) {
                        if (isViewAttached()) {
                            mView.onError(t.getLocalizedMessage());
                            mView.hideLoadingIndicator();
                        }
                    }
                });
    }

    private ArrayList<PostsModel> randomList(ArrayList<PostsModel> arrayList) {
        ArrayList<PostsModel> randomAlbumList = new ArrayList<>();
        Random random = new Random();
        int Low = 15;
        int High = 20;
        int res = random.nextInt(High - Low) + Low;
        for (int i = 0; i < res; i++) {
            randomAlbumList.add(arrayList.get(random.nextInt(arrayList.size())));
            Log.d("AlbumsPresenter", "randomList: " + randomAlbumList.get(i).getTitle());
        }

        return randomAlbumList;
    }

    @Override
    public void bind(PostsContract.View view) {
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
