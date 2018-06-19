package com.example.alex.albumspostsproject.ui.albums_page.photos;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;
import com.example.alex.albumspostsproject.R;
import com.example.alex.albumspostsproject.StartApplication;
import com.example.alex.albumspostsproject.data.entity.PhotoModel;
import com.example.alex.albumspostsproject.ui.base.BaseActivity;
import com.example.alex.albumspostsproject.ui.albums_page.full_screen_image.PhotoFullScreenActivity;

import java.util.ArrayList;

public class PhotosActivity extends BaseActivity implements
        AdapterView.OnItemClickListener, PhotosContract.View {

    private GridView mGridView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photos);
        initToolbar(true);
        mGridView = findViewById(R.id.gridViewPhotos);
        PhotosContract.Presenter presenter = new PhotosPresenter(StartApplication.get(this).getService());

        presenter.bind(this);
        mGridView.setOnItemClickListener(this);
        presenter.loadImageFromAlbum(getIntent().getIntExtra("id", 0));
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent i = new Intent(this, PhotoFullScreenActivity.class).putExtra("imgLink", ((PhotoModel) parent.getItemAtPosition(position)).getUrl());
        if (Build.VERSION.SDK_INT >= 21) {
            View sharedView = mGridView;
            String transitionName = "sasha";
            ActivityOptions transitionActivityOptions = ActivityOptions.makeSceneTransitionAnimation(this, sharedView, transitionName);
            startActivity(i, transitionActivityOptions.toBundle());
        } else {
            startActivity(i);
        }
    }

    @Override
    public void onSuccess(ArrayList<PhotoModel> photoModels) {
        mGridView.setAdapter(new PhotosAdapter(this, photoModels));
    }

    @Override
    public void onError(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
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
