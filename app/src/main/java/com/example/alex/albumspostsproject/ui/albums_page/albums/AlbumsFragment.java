package com.example.alex.albumspostsproject.ui.albums_page.albums;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.example.alex.albumspostsproject.StartApplication;
import com.example.alex.albumspostsproject.data.entity.AlbumModel;
import com.example.alex.albumspostsproject.ui.base.BaseFragment;
import com.example.alex.albumspostsproject.ui.albums_page.photos.PhotosActivity;

import java.util.ArrayList;

import static com.example.alex.albumspostsproject.R.*;

public class AlbumsFragment extends BaseFragment implements AlbumsContract.View, AdapterView.OnItemClickListener {
    private ListView mListView;
    private LinearLayout mLayout;
    private AlbumsContract.Presenter mPresenter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getContext() != null)
            mPresenter = new AlbumsPresenter(StartApplication.get(getContext()).getService());
        mPresenter.bind(this);

        mPresenter.downloadAlbumsList();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(layout.fragment_listview, container, false);
        mListView = view.findViewById(id.listView);
        mLayout = view.findViewById(id.linear);
        Toolbar toolbar = view.findViewById(id.toolbar);
        toolbar.setVisibility(View.GONE);
        return view;
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mListView.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        startActivity(new Intent(getContext(), PhotosActivity.class).putExtra("id", ((AlbumModel) parent.getItemAtPosition(position)).getUserId()));
    }

    @Override
    public void onSuccess(ArrayList<AlbumModel> model) {
        if (getContext() != null)
            mListView.setAdapter(new AlbumsAdapter(getContext(), model));
    }

    @Override
    public void backgroundRandomImage(String url) {
        Glide.with(getContext())
                .load(url)
                .into(new SimpleTarget<Drawable>() {
                    @Override
                    public void onResourceReady(@NonNull Drawable resource, @Nullable Transition<? super Drawable> transition) {
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN)
                            mLayout.setBackground(resource);
                    }
                });
    }

    @Override
    public void onFailture(String msg) {
        Toast.makeText(getContext(), msg, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mPresenter.unbind();
    }

    @Override
    public void showLoadingIndicator() {
        showProgressBar();
    }

    @Override
    public void hideLoadingIndicator() {
        dismissProgressBar();
    }
}
