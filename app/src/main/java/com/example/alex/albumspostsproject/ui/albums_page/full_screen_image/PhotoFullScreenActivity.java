package com.example.alex.albumspostsproject.ui.albums_page.full_screen_image;

import android.os.Bundle;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.alex.albumspostsproject.R;
import com.example.alex.albumspostsproject.ui.base.BaseActivity;

public class PhotoFullScreenActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo_full_screen);
        initToolbar(true);
        ImageView imgFullScreen = findViewById(R.id.imgFullScreen);

        Glide.with(this)
                .load(getIntent().getStringExtra("imgLink"))
                .into(imgFullScreen);

    }



}
