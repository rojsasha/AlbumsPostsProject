package com.example.alex.albumspostsproject.ui.posts_page.comments;

import android.support.annotation.NonNull;

import com.example.alex.albumspostsproject.data.entity.CommentsModel;
import com.example.alex.albumspostsproject.data.network.RetrofitService;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CommentsPresenter implements CommentsContract.Presenter {
    private RetrofitService mService;
    private CommentsContract.View mView;

    CommentsPresenter(RetrofitService service) {
        mService = service;
    }

    @Override
    public void getComments(int postId) {
        mView.showLoadingIndicator();
        mService.geComments(postId)
                .enqueue(new Callback<ArrayList<CommentsModel>>() {
                    @Override
                    public void onResponse(@NonNull Call<ArrayList<CommentsModel>> call, @NonNull Response<ArrayList<CommentsModel>> response) {
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
                    public void onFailure(@NonNull Call<ArrayList<CommentsModel>> call, @NonNull Throwable t) {
                        if (isViewAttached()){
                            mView.onError(t.getLocalizedMessage());
                            mView.hideLoadingIndicator();
                        }

                    }
                });
    }

    @Override
    public void bind(CommentsContract.View view) {
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
